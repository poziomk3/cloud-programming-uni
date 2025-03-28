package com.poziomk3.user_man_service.infrastructure.persistance.repository;

import com.poziomk3.user_man_service.domain.model.User;
import com.poziomk3.user_man_service.domain.repository.UserRepository;
import com.poziomk3.user_man_service.infrastructure.persistance.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final SpringDataUserRepository jpaRepository;
    private final UserMapper mapper;

    public UserRepositoryImpl(SpringDataUserRepository jpaRepository, UserMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(User user) {
        jpaRepository.save(mapper.toEntity(user));
    }

    @Override
    public Optional<User> findById(UUID userId) {
        return jpaRepository.findById(userId)
                .map(mapper::toDomain);
    }
}
