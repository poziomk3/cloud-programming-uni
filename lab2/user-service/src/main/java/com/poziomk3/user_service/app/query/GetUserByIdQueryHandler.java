package com.poziomk3.user_service.app.query;


import com.poziomk3.user_service.domain.model.User;
import com.poziomk3.user_service.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetUserByIdQueryHandler {

    private final UserRepository repository;

    public GetUserByIdQueryHandler(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> handle(UUID id) {
        return repository.findById(id);
    }
}
