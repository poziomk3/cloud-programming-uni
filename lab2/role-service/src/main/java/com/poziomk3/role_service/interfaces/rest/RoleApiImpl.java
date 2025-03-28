package com.poziomk3.role_service.interfaces.rest;

import com.poziomk3.api.RolesApi;
import com.poziomk3.dto.UserRolesResponse;
import com.poziomk3.role_service.app.query.GetUserRolesQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import java.util.UUID;


@RestController
public class RoleApiImpl implements RolesApi {

    private final GetUserRolesQueryHandler queryHandler;

    public RoleApiImpl(GetUserRolesQueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    @Override
    public ResponseEntity<UserRolesResponse> getRolesForUser(UUID userId) {
        var response = queryHandler.handle(userId);
        return ResponseEntity.ok(response);
    }

}
