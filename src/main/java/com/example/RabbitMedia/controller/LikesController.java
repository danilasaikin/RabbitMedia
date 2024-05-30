package com.example.RabbitMedia.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
public class LikesController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public void addLike(@RequestBody String like) {
        rabbitTemplate.convertAndSend("social_network_exchange", "activity.like", like);
    }
}
