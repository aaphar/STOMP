package com.example.messaging_stomp_websocket.service;


import com.example.messaging_stomp_websocket.entities.Post;
import com.example.messaging_stomp_websocket.request.PostRequest;

public interface ChannelService {
    String sendPost(PostRequest postRequest);

}
