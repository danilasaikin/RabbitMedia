package com.example.RabbitMedia.listeners;

import com.example.RabbitMedia.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionsListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitMQConfig.NEW_PUBLICATIONS_QUEUE)
    public void handleNewPublication(String publication) {
        // Логика обработки новых публикаций и отправки уведомлений подписчикам
        String notification = "Новое уведомление для подписчиков о публикации: " + publication;
        rabbitTemplate.convertAndSend("social_network_exchange", "subscriber.notification", notification);
    }
}

