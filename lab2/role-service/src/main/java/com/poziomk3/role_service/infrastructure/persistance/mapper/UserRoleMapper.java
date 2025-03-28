package com.poziomk3.role_service.infrastructure.persistance.mapper;

import com.poziomk3.role_service.domain.model.UserRole;
import com.poziomk3.role_service.infrastructure.persistance.entity.UserRoleEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserRoleMapper {

    UserRoleEntity toEntity(UserRole userRole);

    UserRole toDomain(UserRoleEntity entity);
}
