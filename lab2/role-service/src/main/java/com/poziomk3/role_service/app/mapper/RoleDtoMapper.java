package com.poziomk3.role_service.app.mapper;

import com.poziomk3.dto.UserRolesResponse;
import com.poziomk3.role_service.domain.model.UserRole;
import org.mapstruct.Mapper;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoleDtoMapper {

    default UserRolesResponse toDto(UUID userId, Optional<UserRole> userRoleOpt) {
        var dto = new UserRolesResponse();
        dto.setUserId(userId);
        dto.setRoles(
                userRoleOpt
                        .map(UserRole::getRoles)
                        .orElseGet(Set::of)
                        .stream()
                        .map(Enum::name)
                        .collect(Collectors.toList())
        );
        return dto;
    }

}
