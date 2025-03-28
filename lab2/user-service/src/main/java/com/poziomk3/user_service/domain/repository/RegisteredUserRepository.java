package com.poziomk3.user_service.domain.repository;

import com.poziomk3.user_service.domain.model.RegisteredUser;

import java.util.Optional;
import java.util.UUID;


public interface RegisteredUserRepository {

    void save(RegisteredUser user);

    Optional<RegisteredUser> findById(UUID userId);
}
