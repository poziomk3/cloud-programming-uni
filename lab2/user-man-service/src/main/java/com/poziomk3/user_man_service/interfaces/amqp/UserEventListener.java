package com.poziomk3.user_man_service.interfaces.amqp;

import com.poziomk3.dto.UserRegisteredEvent;
import com.poziomk3.user_man_service.app.command.RegisterUserCommand;
import com.poziomk3.user_man_service.app.command.RegisterUserCommandHandler;
import com.poziomk3.user_man_service.app.mapper.RegisterUserCommandMapper;
import com.poziomk3.user_man_service.config.RabbitMQConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class UserEventListener {

    private final RegisterUserCommandHandler handler;
    private final RegisterUserCommandMapper registerUserCommandMapper;


    @RabbitListener(queues = RabbitMQConfig.USER_REGISTERED_QUEUE)
    public void handle(UserRegisteredEvent event) {
        log.info("Received UserRegisteredEvent for email: {}", event.getEmail());
        RegisterUserCommand command = registerUserCommandMapper.toCommand(event);
        handler.handle(command);
        log.info("Handled RegisterUserCommand for email: {}", command.email());
    }
}
