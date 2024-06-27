package com.example.messaging_stomp_websocket.controller;

import com.example.messaging_stomp_websocket.request.MessageRequest;
import com.example.messaging_stomp_websocket.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @Autowired
    ChatService privateChatService;

    @MessageMapping("/private-message")
    @SendToUser("/queue/reply")
    public String sendPrivateMessage(MessageRequest messageRequest) throws Exception {
        Thread.sleep(1000); // simulated delay
        return privateChatService.sendMessage(messageRequest);
    }
}
