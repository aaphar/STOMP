package com.example.messaging_stomp_websocket.service;

import com.example.messaging_stomp_websocket.entities.User;
import com.example.messaging_stomp_websocket.entities.chat.Message;
import com.example.messaging_stomp_websocket.repositories.MessageRepository;
import com.example.messaging_stomp_websocket.repositories.UserRepository;
import com.example.messaging_stomp_websocket.request.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    public String sendMessage(MessageRequest messageRequest) {
        User sender = userRepository.findById(messageRequest.getSenderId()).orElseThrow(() -> new RuntimeException("Sender not found"));
        User recipient = userRepository.findById(messageRequest.getRecipientId()).orElseThrow(() -> new RuntimeException("Recipient not found"));

        Message message = Message.builder()
                .content(messageRequest.getContent())
                .sender(sender)
                .recipient(recipient)
                .build();
        Message savedMessage = messageRepository.save(message);
        return savedMessage.getContent() + " " + savedMessage.getSender().getUsername();
    }
}
