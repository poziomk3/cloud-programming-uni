package com.poziomk3.user_man_service.app.command;

import com.poziomk3.dto.UserCreatedEvent;
import com.poziomk3.user_man_service.domain.model.User;
import com.poziomk3.user_man_service.domain.repository.UserRepository;
import com.poziomk3.user_man_service.infrastructure.messaging.UserEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserCommandHandler {

    private final UserRepository userRepository;
    private final UserEventPublisher eventPublisher;

    public RegisterUserCommandHandler(UserRepository userRepository, UserEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
    }

    public void handle(RegisterUserCommand command) {
        User user = new User(
                command.userId(),
                command.email(),
                command.password(),
                null,
                null,
                command.createdAt()
        );

        userRepository.save(user);

        UserCreatedEvent createdEvent = new UserCreatedEvent()
                .userId(user.userId())
                .email(user.email())
                .createdAt(user.createdAt());

        eventPublisher.publish(createdEvent);
    }
}
