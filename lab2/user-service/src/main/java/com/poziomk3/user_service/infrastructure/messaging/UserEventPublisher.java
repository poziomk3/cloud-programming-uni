package com.poziomk3.user_service.infrastructure.messaging;

import com.poziomk3.dto.UserRegisteredEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static com.poziomk3.user_service.config.RabbitMQConfig.*;

@Component
public class UserEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public UserEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishUserRegistered(UserRegisteredEvent event) {
        rabbitTemplate.convertAndSend(USER_EXCHANGE, USER_ROUTING_KEY, event);
    }
}
