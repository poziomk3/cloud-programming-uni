package com.poziomk3.user_service.infrastructure.persistance.repository;

import com.poziomk3.user_service.domain.model.User;
import com.poziomk3.user_service.domain.repository.UserRepository;
import com.poziomk3.user_service.infrastructure.persistance.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaUserRepository implements UserRepository {

    private final SpringDataUserRepository springDataRepo;
    private final UserMapper mapper;

    public JpaUserRepository(SpringDataUserRepository springDataRepo, UserMapper mapper) {
        this.springDataRepo = springDataRepo;
        this.mapper = mapper;
    }

    @Override
    public void save(User user) {
        springDataRepo.save(mapper.toEntity(user));
    }

    @Override
    public Optional<User> findById(UUID id) {
        return springDataRepo.findById(id).map(mapper::toDomain);
    }
}
