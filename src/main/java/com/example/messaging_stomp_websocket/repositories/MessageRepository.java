package com.example.messaging_stomp_websocket.repositories;

import com.example.messaging_stomp_websocket.entities.chat.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
}
