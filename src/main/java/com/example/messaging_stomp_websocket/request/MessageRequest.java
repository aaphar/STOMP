package com.example.messaging_stomp_websocket.request;

import lombok.Data;

@Data
public class MessageRequest {
    private String content;
    private String senderId;
    private String recipientId;
}
