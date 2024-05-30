package com.example.RabbitMedia.listeners;

import com.example.RabbitMedia.config.RabbitMQConfig;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationsListener {

    @RabbitListener(queues = RabbitMQConfig.SUBSCRIBER_NOTIFICATIONS_QUEUE)
    public void handleSubscriberNotification(String notification) {
        // Логика отправки уведомлений подписчикам
        log.info("Отправка уведомления подписчику: " + notification);
    }
}
