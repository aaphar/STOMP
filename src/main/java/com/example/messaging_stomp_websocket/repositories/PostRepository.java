package com.example.messaging_stomp_websocket.repositories;


import com.example.messaging_stomp_websocket.entities.Post;
import com.example.messaging_stomp_websocket.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PostRepository extends MongoRepository<Post, String> {

    Optional<Post> findByAuthor(User user);
}
