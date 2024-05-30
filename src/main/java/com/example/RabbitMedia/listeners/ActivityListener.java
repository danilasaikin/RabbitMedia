package com.example.RabbitMedia.listeners;

import com.example.RabbitMedia.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ActivityListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitMQConfig.NEW_LIKE_QUEUE)
    public void handleNewLike(String like) {
        // Логика обработки нового лайка
        String notification = "Уведомление о новом лайке: " + like;
        rabbitTemplate.convertAndSend("social_network_exchange", "subscriber.notification", notification);
        log.info("Обработка нового лайка: " + like);
    }

    @RabbitListener(queues = RabbitMQConfig.NEW_COMMENT_QUEUE)
    public void handleNewComment(String comment) {
        // Логика обработки нового комментария
        String notification = "Уведомление о новом комментарии: " + comment;
        rabbitTemplate.convertAndSend("social_network_exchange", "subscriber.notification", notification);
        log.info("Обработка нового комментария: " + comment);
    }
}
