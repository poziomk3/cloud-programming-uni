package com.poziomk3.user_man_service.app.query;

import com.poziomk3.dto.FullUserDto;
import com.poziomk3.user_man_service.app.mapper.UserDtoMapper;
import com.poziomk3.user_man_service.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class GetUserByIdQueryHandler {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;


    public Optional<FullUserDto> handle(GetUserByIdQuery query) {
        log.info("Handling GetUserByIdQuery for userId: {}", query.userId());

        return userRepository.findById(query.userId())
                .map(user -> {
                    log.info("User found for userId: {}", query.userId());
                    return userDtoMapper.toDto(user);
                });
    }
}
