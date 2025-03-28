package com.poziomk3.user_service.infrastructure.persistance.repository;


import com.poziomk3.user_service.infrastructure.persistance.entity.RegisteredUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataRegisteredUserRepository extends JpaRepository<RegisteredUserEntity, UUID> {
}
