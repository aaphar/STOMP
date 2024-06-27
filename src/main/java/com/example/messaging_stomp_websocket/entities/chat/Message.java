package com.example.messaging_stomp_websocket.entities.chat;

import com.example.messaging_stomp_websocket.entities.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    @Id
    private String id;
    private String content;

    @DBRef
    private User sender;

    @DBRef
    private User recipient;
}