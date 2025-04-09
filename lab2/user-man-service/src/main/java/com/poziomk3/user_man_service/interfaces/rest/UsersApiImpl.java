package com.poziomk3.user_man_service.interfaces.rest;

import com.poziomk3.api.UsersApi;
import com.poziomk3.dto.FullUserDto;
import com.poziomk3.user_man_service.app.query.GetUserByIdQuery;
import com.poziomk3.user_man_service.app.query.GetUserByIdQueryHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Slf4j
@Controller
@AllArgsConstructor
public class UsersApiImpl implements UsersApi {

    private final GetUserByIdQueryHandler queryHandler;


    @Override
    public ResponseEntity<FullUserDto> getFullUserById(UUID id) {
        log.info("Received request to get full user by ID: {}", id);

        return queryHandler.handle(new GetUserByIdQuery(id))
                .map(user -> {
                    log.info("User found for ID: {}", id);
                    return ResponseEntity.ok(user);
                })
                .orElseGet(() -> {
                    log.warn("User not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }
}
