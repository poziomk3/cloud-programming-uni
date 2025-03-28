package com.poziomk3.role_service.interfaces.amqp;

import com.poziomk3.dto.UserCreatedEvent;
import com.poziomk3.role_service.app.command.AssignDefaultRoleHandler;
import com.poziomk3.role_service.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedEventListener {

    private final AssignDefaultRoleHandler handler;

    public UserCreatedEventListener(AssignDefaultRoleHandler handler) {
        this.handler = handler;
    }

    @RabbitListener(queues = RabbitMQConfig.USER_CREATED_QUEUE)
    public void handle(UserCreatedEvent event) {
        handler.handle(event.getUserId());
    }
}
