package com.poziomk3.user_man_service.domain.repository;


import com.poziomk3.user_man_service.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    void save(User user);

    Optional<User> findById(UUID userId);
}
