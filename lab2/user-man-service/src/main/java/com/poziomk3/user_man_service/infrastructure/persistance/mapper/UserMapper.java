package com.poziomk3.user_man_service.infrastructure.persistance.mapper;


import com.poziomk3.user_man_service.domain.model.User;
import com.poziomk3.user_man_service.infrastructure.persistance.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(User domain);

    User toDomain(UserEntity entity);
}
