package com.poziomk3.user_service.interfaces.rest;


import com.poziomk3.api.UsersApi;
import com.poziomk3.dto.MinimalUserDto;
import com.poziomk3.dto.RegisterUserRequest;
import com.poziomk3.dto.RegisterUserResponse;
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
        UUID userId = registerHandler.handle(command); // get generated userId

        var response = new RegisterUserResponse();
        response.setUserId(userId); // <--- set it!

        return ResponseEntity.status(201).body(response);
    }

    @Override
    public ResponseEntity<MinimalUserDto> getUserById(UUID id) {
        return queryHandler.handle(id)
                .map(user -> {
                    var dto = new MinimalUserDto();
                    dto.setUserId(UUID.fromString(user.userId().toString()));
                    dto.setEmail(user.email());
                    dto.setCreatedAt(user.createdAt());
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
