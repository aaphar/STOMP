package com.example.messaging_stomp_websocket.controller;

import com.example.messaging_stomp_websocket.entities.Post;
import com.example.messaging_stomp_websocket.request.PostRequest;
import com.example.messaging_stomp_websocket.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChannelController {

    @Autowired
    ChannelService channelService;

    @MessageMapping("/channel")
    @SendTo("/topic/channel")
    public String sendPost(PostRequest postRequest) throws Exception {
        Thread.sleep(1000); // simulated delay
        return channelService.sendPost(postRequest);
    }
}
