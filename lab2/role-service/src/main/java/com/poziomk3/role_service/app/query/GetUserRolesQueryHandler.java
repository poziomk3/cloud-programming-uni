package com.poziomk3.role_service.app.query;

import com.poziomk3.dto.UserRolesResponse;
import com.poziomk3.role_service.app.mapper.RoleDtoMapper;
import com.poziomk3.role_service.domain.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class GetUserRolesQueryHandler {

    private final RoleRepository repository;
    private final RoleDtoMapper mapper;

    public GetUserRolesQueryHandler(RoleRepository repository, @Qualifier("roleDtoMapperImpl") RoleDtoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UserRolesResponse handle(UUID userId) {
        log.info("Handling getRoles query for userId: {}", userId);
        var roles = repository.findByUserId(userId);
        log.info("Found {} role(s) for userId: {}", roles, userId);
        return mapper.toDto(userId, roles);
    }
}
