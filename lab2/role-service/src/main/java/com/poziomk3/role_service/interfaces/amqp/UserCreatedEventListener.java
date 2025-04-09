package com.poziomk3.role_service.interfaces.amqp;

import com.poziomk3.dto.UserCreatedEvent;
import com.poziomk3.role_service.app.command.AssignDefaultRoleCommand;
import com.poziomk3.role_service.app.command.AssignDefaultRoleHandler;
import com.poziomk3.role_service.config.RabbitMQConfig;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserCreatedEventListener {

    private final AssignDefaultRoleHandler handler;

    @RabbitListener(queues = RabbitMQConfig.USER_CREATED_QUEUE)
    public void handle(UserCreatedEvent event) {
        AssignDefaultRoleCommand command = new AssignDefaultRoleCommand(event.getUserId(), event.getEmail());
        handler.handle(command);
    }
}
