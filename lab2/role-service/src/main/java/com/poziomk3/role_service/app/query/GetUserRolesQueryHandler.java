package com.poziomk3.role_service.app.query;


import com.poziomk3.dto.UserRolesResponse;
import com.poziomk3.role_service.app.mapper.RoleDtoMapper;
import com.poziomk3.role_service.domain.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetUserRolesQueryHandler {

    private final RoleRepository repository;
    private final RoleDtoMapper mapper;

    public GetUserRolesQueryHandler(RoleRepository repository, RoleDtoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UserRolesResponse handle(UUID userId) {
        var roles = repository.findByUserId(userId);
        return mapper.toDto(userId, roles);
    }
}
