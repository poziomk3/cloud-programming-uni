package com.poziomk3.user_man_service.interfaces.rest;

import com.poziomk3.api.UsersApi;
import com.poziomk3.dto.FullUserDto;
import com.poziomk3.user_man_service.app.query.GetUserByIdQuery;
import com.poziomk3.user_man_service.app.query.GetUserByIdQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class UsersApiImpl implements UsersApi {

    private final GetUserByIdQueryHandler queryHandler;

    public UsersApiImpl(GetUserByIdQueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    @Override
    public ResponseEntity<FullUserDto> getFullUserById(UUID id) {
        return queryHandler.handle(new GetUserByIdQuery(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
