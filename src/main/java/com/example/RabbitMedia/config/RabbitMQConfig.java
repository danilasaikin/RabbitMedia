package com.example.RabbitMedia.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Объявление очередей
    public static final String NEW_PUBLICATIONS_QUEUE = "новые_публикации";
    public static final String SUBSCRIBER_NOTIFICATIONS_QUEUE = "уведомления_подписчикам";
    public static final String NEW_LIKE_QUEUE = "новый_лайк";
    public static final String NEW_COMMENT_QUEUE = "новый_комментарий";

    @Bean
    public Queue newPublicationsQueue() {
        return new Queue(NEW_PUBLICATIONS_QUEUE, true);
    }

    @Bean
    public Queue subscriberNotificationsQueue() {
        return new Queue(SUBSCRIBER_NOTIFICATIONS_QUEUE, true);
    }

    @Bean
    public Queue newLikeQueue() {
        return new Queue(NEW_LIKE_QUEUE, true);
    }

    @Bean
    public Queue newCommentQueue() {
        return new Queue(NEW_COMMENT_QUEUE, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("social_network_exchange");
    }

    @Bean
    public Binding bindingNewPublications(Queue newPublicationsQueue, TopicExchange exchange) {
        return BindingBuilder.bind(newPublicationsQueue).to(exchange).with("new.publication.#");
    }

    @Bean
    public Binding bindingSubscriberNotifications(Queue subscriberNotificationsQueue, TopicExchange exchange) {
        return BindingBuilder.bind(subscriberNotificationsQueue).to(exchange).with("subscriber.notification.#");
    }

    @Bean
    public Binding bindingNewLike(Queue newLikeQueue, TopicExchange exchange) {
        return BindingBuilder.bind(newLikeQueue).to(exchange).with("activity.like.#");
    }

    @Bean
    public Binding bindingNewComment(Queue newCommentQueue, TopicExchange exchange) {
        return BindingBuilder.bind(newCommentQueue).to(exchange).with("activity.comment.#");
    }
}

