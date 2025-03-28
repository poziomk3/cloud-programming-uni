package com.poziomk3.user_service.app.query;


import com.poziomk3.user_service.domain.model.RegisteredUser;
import com.poziomk3.user_service.domain.repository.RegisteredUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetUserByIdQueryHandler {

    private final RegisteredUserRepository repository;

    public GetUserByIdQueryHandler(RegisteredUserRepository repository) {
        this.repository = repository;
    }

    public Optional<RegisteredUser> handle(UUID id) {
        return repository.findById(id);
    }
}
