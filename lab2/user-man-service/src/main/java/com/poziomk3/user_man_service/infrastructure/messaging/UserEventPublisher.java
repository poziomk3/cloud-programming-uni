package com.poziomk3.user_man_service.infrastructure.messaging;

import com.poziomk3.dto.UserCreatedEvent;
import com.poziomk3.user_man_service.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public UserEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(UserCreatedEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.USER_EXCHANGE,
                RabbitMQConfig.USER_CREATED_ROUTING_KEY,
                event
        );
    }
}