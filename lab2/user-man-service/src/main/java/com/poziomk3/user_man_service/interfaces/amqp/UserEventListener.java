package com.poziomk3.user_man_service.interfaces.amqp;

import com.poziomk3.dto.UserRegisteredEvent;
import com.poziomk3.user_man_service.app.command.RegisterUserCommand;
import com.poziomk3.user_man_service.app.command.RegisterUserCommandHandler;
import com.poziomk3.user_man_service.app.mapper.RegisterUserCommandMapper;
import com.poziomk3.user_man_service.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserEventListener {

    private final RegisterUserCommandHandler handler;
    private final RegisterUserCommandMapper mapper;

    public UserEventListener(RegisterUserCommandHandler handler, RegisterUserCommandMapper mapper) {
        this.handler = handler;
        this.mapper = mapper;
    }

    @RabbitListener(queues = RabbitMQConfig.USER_REGISTERED_QUEUE)
    public void handle(UserRegisteredEvent event) {
        log.info("Received UserRegisteredEvent for email: {}", event.getEmail());
        RegisterUserCommand command = mapper.toCommand(event);
        handler.handle(command);
        log.info("Handled RegisterUserCommand for email: {}", command.email());
    }
}
