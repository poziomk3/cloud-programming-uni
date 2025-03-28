package com.poziomk3.role_service.domain.repository;


import com.poziomk3.role_service.domain.model.UserRole;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository {
    Optional<UserRole> findByUserId(UUID userId);

    void save(UserRole userRole);
}
