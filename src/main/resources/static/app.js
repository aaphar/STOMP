const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/gs-guide-websocket'
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);

    // Subscribe to public channel
    stompClient.subscribe('/topic/channel', (greeting) => {
        const messageBody = new TextDecoder("utf-8").decode(greeting.binaryBody);
        const parsedMessage = JSON.parse(messageBody);
        console.log(parsedMessage);
        showGreeting(parsedMessage.content);
    });

    // Subscribe to user-specific queue for private messages
    stompClient.subscribe('/user/queue/reply', (message) => {
        const messageBody = new TextDecoder("utf-8").decode(message.binaryBody);
        const parsedMessage = JSON.parse(messageBody);
        console.log(parsedMessage);
        showPrivateMessage(parsedMessage.content);
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
    $("#privateMessages").html("");
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.publish({
        destination: "/app/channel",
        body: JSON.stringify({
            'content': $("#name").val(),
            'authorId': $("#sender").val()
        })
    });
}

function sendPrivateMessage() {
    const recipientId = $("#recipient").val();
    const content = $("#privateMessage").val();
    stompClient.publish({
        destination: "/app/private-message",
        body: JSON.stringify({
            'content': content,
            'senderId': $("#senderPrivate").val(),
            'recipientId': recipientId
        })
    });
}

function showGreeting(message) {
    console.log(message);
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

function showPrivateMessage(message) {
    console.log(message);
    $("#privateMessages").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $("#connect").click(() => connect());
    $("#disconnect").click(() => disconnect());
    $("#send").click(() => sendName());
    $("#sendPrivate").click(() => sendPrivateMessage());
});
