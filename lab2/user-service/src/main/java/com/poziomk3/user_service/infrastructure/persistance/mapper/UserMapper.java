package com.poziomk3.user_service.infrastructure.persistance.mapper;

import com.poziomk3.user_service.domain.model.User;
import com.poziomk3.user_service.infrastructure.persistance.entity.UserEntity;
import org.mapstruct.Mapper;


import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "passwordHash", source = "passwordHash")
    User toDomain(UserEntity entity);

    UserEntity toEntity(User user);
}