package com.poziomk3.role_service.interfaces.rest;

import com.poziomk3.api.RolesApi;
import com.poziomk3.dto.UserRolesResponse;
import com.poziomk3.role_service.app.query.GetUserRolesQueryHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
public class RoleApiImpl implements RolesApi {

    private final GetUserRolesQueryHandler queryHandler;

    @Override
    public ResponseEntity<UserRolesResponse> getRolesForUser(UUID userId) {
        log.info("Received request for roles of user with ID: {}", userId);
        var response = queryHandler.handle(userId);
        log.info("Roles fetched for user {}: {}", userId, response.getRoles());
        return ResponseEntity.ok(response);
    }
}
