package com.poziomk3.user_service.infrastructure.persistance.repository;


import com.poziomk3.user_service.infrastructure.persistance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, UUID> {
}
