package com.poziomk3.role_service.infrastructure.persistance.repository;

import com.poziomk3.role_service.infrastructure.persistance.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataUserRoleRepository extends JpaRepository<UserRoleEntity, UUID> {
}
