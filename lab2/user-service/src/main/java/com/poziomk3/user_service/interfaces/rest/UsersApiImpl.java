package com.poziomk3.user_service.interfaces.rest;


import com.poziomk3.api.UsersApi;
import com.poziomk3.dto.RegisterUserRequest;
import com.poziomk3.dto.RegisterUserResponse;
import com.poziomk3.dto.UserDto;
import com.poziomk3.user_service.app.command.RegisterUserCommand;
import com.poziomk3.user_service.app.command.RegisterUserCommandHandler;
import com.poziomk3.user_service.app.query.GetUserByIdQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UsersApiImpl implements UsersApi {

    private final RegisterUserCommandHandler registerHandler;
    private final GetUserByIdQueryHandler queryHandler;

    public UsersApiImpl(
            RegisterUserCommandHandler registerHandler,
            GetUserByIdQueryHandler queryHandler
    ) {
        this.registerHandler = registerHandler;
        this.queryHandler = queryHandler;
    }

    @Override
    public ResponseEntity<RegisterUserResponse> registerUser(RegisterUserRequest request) {
        var command = new RegisterUserCommand(request.getEmail(), request.getPassword());
        var result = registerHandler.handle(command);

        var response = new RegisterUserResponse();
        response.setUserId(UUID.fromString(result.userId().toString()));

        return ResponseEntity.status(201).body(response);
    }

    @Override
    public ResponseEntity<UserDto> getUserById(UUID id) {
        return queryHandler.handle(id)
                .map(user -> {
                    var dto = new UserDto();
                    dto.setUserId(UUID.fromString(user.getId().toString()));
                    dto.setEmail(user.getEmail());
                    dto.setCreatedAt(user.getCreatedAt());
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
