package com.poziomk3.role_service.infrastructure.persistance.repository;

import com.poziomk3.role_service.domain.model.UserRole;
import com.poziomk3.role_service.domain.repository.RoleRepository;
import com.poziomk3.role_service.infrastructure.persistance.mapper.UserRoleMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private final SpringDataUserRoleRepository jpaRepo;
    private final UserRoleMapper mapper;

    public RoleRepositoryImpl(SpringDataUserRoleRepository jpaRepo, UserRoleMapper mapper) {
        this.jpaRepo = jpaRepo;
        this.mapper = mapper;
    }

    @Override
    public Optional<UserRole> findByUserId(UUID userId) {
        return jpaRepo.findById(userId).map(mapper::toDomain);
    }

    @Override
    public void save(UserRole userRole) {
        jpaRepo.save(mapper.toEntity(userRole));
    }
}

