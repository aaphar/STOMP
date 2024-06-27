package com.example.messaging_stomp_websocket.service;


import com.example.messaging_stomp_websocket.entities.Post;
import com.example.messaging_stomp_websocket.entities.User;
import com.example.messaging_stomp_websocket.repositories.PostRepository;
import com.example.messaging_stomp_websocket.repositories.UserRepository;
import com.example.messaging_stomp_websocket.request.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public String sendPost(PostRequest postRequest) {
        User author = userRepository.findById(postRequest.getAuthorId()).orElseThrow(() -> new RuntimeException("User not found"));
        Post post = Post.builder()
                .content(postRequest.getContent())
                .author(author)
                .build();
        Post savedPost = postRepository.insert(post);
        return savedPost.getContent() + " " + savedPost.getAuthor().getUsername();
    }

}
