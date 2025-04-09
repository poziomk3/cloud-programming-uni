package com.poziomk3.user_man_service.app.mapper;

import com.poziomk3.dto.UserCreatedEvent;
import com.poziomk3.user_man_service.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserEventMapper {

    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "createdAt", source = "createdAt")
    UserCreatedEvent toUserCreatedEvent(User user);
}