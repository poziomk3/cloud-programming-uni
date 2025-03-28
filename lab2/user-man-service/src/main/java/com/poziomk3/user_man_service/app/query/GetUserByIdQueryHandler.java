package com.poziomk3.user_man_service.app.query;


import com.poziomk3.dto.FullUserDto;
import com.poziomk3.user_man_service.app.mapper.UserDtoMapper;
import com.poziomk3.user_man_service.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetUserByIdQueryHandler {

    private final UserRepository userRepository;
    private final UserDtoMapper mapper;

    public GetUserByIdQueryHandler(UserRepository userRepository, UserDtoMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public Optional<FullUserDto> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId())
                .map(mapper::toDto);
    }
}


