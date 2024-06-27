package com.example.messaging_stomp_websocket.service;

import com.example.messaging_stomp_websocket.request.MessageRequest;

public interface ChatService {
    String sendMessage(MessageRequest messageRequest);
}
