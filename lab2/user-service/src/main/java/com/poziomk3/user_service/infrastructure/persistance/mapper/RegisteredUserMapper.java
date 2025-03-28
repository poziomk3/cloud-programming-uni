package com.poziomk3.user_service.infrastructure.persistance.mapper;

import com.poziomk3.user_service.domain.model.RegisteredUser;
import com.poziomk3.user_service.infrastructure.persistance.entity.RegisteredUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegisteredUserMapper {

    @Mapping(target = "id", source = "userId")
    RegisteredUserEntity toEntity(RegisteredUser domain);

    RegisteredUser toDomain(RegisteredUserEntity entity);
}
