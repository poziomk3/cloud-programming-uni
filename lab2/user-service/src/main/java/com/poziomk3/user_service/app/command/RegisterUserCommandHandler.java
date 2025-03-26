package com.poziomk3.user_service.app.command;

import com.poziomk3.dto.UserRegisteredEvent;
import com.poziomk3.user_service.domain.model.User;
import com.poziomk3.user_service.domain.repository.UserRepository;
import com.poziomk3.user_service.infrastructure.messaging.UserEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegisterUserCommandHandler {


    private final UserRepository userRepository;
    private final UserEventPublisher eventPublisher;

    public RegisterUserCommandHandler(UserRepository userRepository, UserEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
    }

    public RegisterUserResult handle(RegisterUserCommand command) {
        log.info("Received register command for email: {}", command.email());

        String hashedPassword = command.password() + "hashedPassword";

        User user = User.register(command.email(), hashedPassword);
        log.debug("Created user domain object: {}", user);

        userRepository.save(user);
        log.info("User saved with ID: {}", user.getId());

        UserRegisteredEvent event = new UserRegisteredEvent()
                .userId(user.getId())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt());

        log.info("Publishing UserRegisteredEvent: {}", event);
        eventPublisher.publishUserRegistered(event);

        return new RegisterUserResult(user.getId());
    }
}
