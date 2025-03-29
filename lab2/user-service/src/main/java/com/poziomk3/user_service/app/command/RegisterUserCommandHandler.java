package com.poziomk3.user_service.app.command;

import com.poziomk3.dto.UserRegisteredEvent;
import com.poziomk3.user_service.domain.model.RegisteredUser;
import com.poziomk3.user_service.domain.repository.RegisteredUserRepository;
import com.poziomk3.user_service.infrastructure.messaging.UserEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Slf4j
@Service
public class RegisterUserCommandHandler {

    private final RegisteredUserRepository userRepository;
    private final UserEventPublisher eventPublisher;

    public RegisterUserCommandHandler(RegisteredUserRepository userRepository,
                                      UserEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
    }

    public UUID handle(RegisterUserCommand command) {
        log.info("Handling RegisterUserCommand for email: {}", command.email());

        UUID userId = UUID.randomUUID();
        OffsetDateTime createdAt = OffsetDateTime.now();

        RegisteredUser user = new RegisteredUser(userId, command.email(), createdAt);
        userRepository.save(user);
        log.info("User saved with ID: {}", userId);

        UserRegisteredEvent event = new UserRegisteredEvent(
                userId,
                command.email(),
                command.password(),
                createdAt
        );

        eventPublisher.publishUserRegistered(event);
        log.info("Published UserRegisteredEvent for user ID: {}", userId);

        return userId;
    }

}
