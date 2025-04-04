package com.poziomk3.user_man_service.app.query;

import com.poziomk3.dto.FullUserDto;
import com.poziomk3.user_man_service.app.mapper.UserDtoMapper;
import com.poziomk3.user_man_service.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class GetUserByIdQueryHandler {

    private final UserRepository userRepository;
    private final UserDtoMapper mapper;

    public GetUserByIdQueryHandler(UserRepository userRepository, @Qualifier("userDtoMapperImpl") UserDtoMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public Optional<FullUserDto> handle(GetUserByIdQuery query) {
        log.info("Handling GetUserByIdQuery for userId: {}", query.userId());

        return userRepository.findById(query.userId())
                .map(user -> {
                    log.info("User found for userId: {}", query.userId());
                    return mapper.toDto(user);
                });
    }
}
