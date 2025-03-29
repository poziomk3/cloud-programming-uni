package com.poziomk3.user_service.app.query;


import com.poziomk3.user_service.domain.model.RegisteredUser;
import com.poziomk3.user_service.domain.repository.RegisteredUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class GetUserByIdQueryHandler {

    private final RegisteredUserRepository repository;

    public GetUserByIdQueryHandler(RegisteredUserRepository repository) {
        this.repository = repository;
    }

    public Optional<RegisteredUser> handle(UUID id) {
        log.info("Handling GetUserByIdQuery for ID: {}", id);
        Optional<RegisteredUser> user = repository.findById(id);
        log.info("User found: {}", user.isPresent());
        return user;
    }
}
