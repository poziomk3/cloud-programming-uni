package com.poziomk3.user_service.interfaces.rest;

import com.poziomk3.api.UsersApi;
import com.poziomk3.dto.MinimalUserDto;
import com.poziomk3.dto.RegisterUserRequest;
import com.poziomk3.dto.RegisterUserResponse;
import com.poziomk3.user_service.app.command.RegisterUserCommandHandler;
import com.poziomk3.user_service.app.mapper.UserApiMapper;
import com.poziomk3.user_service.app.query.GetUserByIdQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UsersApiImpl implements UsersApi {

    private final RegisterUserCommandHandler registerHandler;
    private final GetUserByIdQueryHandler queryHandler;
    private final UserApiMapper mapper;

    public UsersApiImpl(
            RegisterUserCommandHandler registerHandler,
            GetUserByIdQueryHandler queryHandler,
            UserApiMapper mapper
    ) {
        this.registerHandler = registerHandler;
        this.queryHandler = queryHandler;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<RegisterUserResponse> registerUser(RegisterUserRequest request) {
        var command = mapper.toCommand(request);
        UUID userId = registerHandler.handle(command);

        var response = new RegisterUserResponse();
        response.setUserId(userId);
        return ResponseEntity.status(201).body(response);
    }

    @Override
    public ResponseEntity<MinimalUserDto> getUserById(UUID id) {
        return queryHandler.handle(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
