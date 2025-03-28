package com.poziomk3.user_service.infrastructure.persistance.repository;

import com.poziomk3.user_service.domain.model.RegisteredUser;
import com.poziomk3.user_service.domain.repository.RegisteredUserRepository;
import com.poziomk3.user_service.infrastructure.persistance.mapper.RegisteredUserMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class RegisteredUserRepositoryImpl implements RegisteredUserRepository {

    private final SpringDataRegisteredUserRepository springDataUserRepository;
    private final RegisteredUserMapper userMapper;

    public RegisteredUserRepositoryImpl(SpringDataRegisteredUserRepository springDataUserRepository,
                                        RegisteredUserMapper userMapper) {
        this.springDataUserRepository = springDataUserRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void save(RegisteredUser user) {
        springDataUserRepository.save(userMapper.toEntity(user));
    }

    @Override
    public Optional<RegisteredUser> findById(UUID id) {
        return springDataUserRepository.findById(id)
                .map(userMapper::toDomain);
    }
}
