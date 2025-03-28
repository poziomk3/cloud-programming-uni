package com.poziomk3.role_service.infrastructure.messaging;


import com.poziomk3.dto.RoleAssignedEvent;
import com.poziomk3.role_service.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RoleEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public RoleEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(RoleAssignedEvent event) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.USER_EXCHANGE, RabbitMQConfig.ROLE_ASSIGNED_ROUTING_KEY, event);
    }
}
