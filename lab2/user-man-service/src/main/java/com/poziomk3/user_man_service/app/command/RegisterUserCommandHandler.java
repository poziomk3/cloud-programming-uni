package com.poziomk3.user_man_service.app.command;

import com.poziomk3.dto.UserCreatedEvent;
import com.poziomk3.user_man_service.domain.model.User;
import com.poziomk3.user_man_service.domain.repository.UserRepository;
import com.poziomk3.user_man_service.infrastructure.messaging.UserEventPublisher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RegisterUserCommandHandler {

    private final UserRepository userRepository;
    private final UserEventPublisher eventPublisher;


    public void handle(RegisterUserCommand command) {
        log.info("Handling RegisterUserCommand for email: {}", command.email());

        User user = new User(
                command.userId(),
                command.email(),
                command.password(),
                null,
                null,
                command.createdAt()
        );

        userRepository.save(user);
        log.info("User saved with ID: {}", user.userId());

        UserCreatedEvent createdEvent = new UserCreatedEvent()
                .userId(user.userId())
                .email(user.email())
                .createdAt(user.createdAt());

        eventPublisher.publish(createdEvent);
        log.info("UserCreatedEvent published for userId: {}", user.userId());
    }
}
