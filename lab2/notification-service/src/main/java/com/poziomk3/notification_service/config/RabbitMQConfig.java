package com.poziomk3.notification_service.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String USER_EXCHANGE = "user.events";

    public static final String USER_CREATED_QUEUE = "notification.user-created.queue";
    public static final String ROLE_ASSIGNED_QUEUE = "notification.role-assigned.queue";

    public static final String USER_CREATED_ROUTING_KEY = "user.created";
    public static final String ROLE_ASSIGNED_ROUTING_KEY = "role.assigned";

    // Exchange
    @Bean
    public TopicExchange userExchange() {
        return new TopicExchange(USER_EXCHANGE, true, false);
    }

    // Queues
    @Bean
    public Queue userCreatedQueue() {
        return new Queue(USER_CREATED_QUEUE, true);
    }

    @Bean
    public Queue roleAssignedQueue() {
        return new Queue(ROLE_ASSIGNED_QUEUE, true);
    }

    // Bindings
    @Bean
    public Binding userCreatedBinding(Queue userCreatedQueue, TopicExchange userExchange) {
        return BindingBuilder.bind(userCreatedQueue).to(userExchange).with(USER_CREATED_ROUTING_KEY);
    }

    @Bean
    public Binding roleAssignedBinding(Queue roleAssignedQueue, TopicExchange userExchange) {
        return BindingBuilder.bind(roleAssignedQueue).to(userExchange).with(ROLE_ASSIGNED_ROUTING_KEY);
    }

    // RabbitTemplate with JSON converter
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }
}
