package com.poziomk3.role_service.app.command;

import com.poziomk3.dto.RoleAssignedEvent;
import com.poziomk3.role_service.domain.model.UserRole;
import com.poziomk3.role_service.domain.model.Role;
import com.poziomk3.role_service.domain.repository.RoleRepository;
import com.poziomk3.role_service.infrastructure.messaging.RoleEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class AssignDefaultRoleHandler {

    private final RoleRepository repository;
    private final RoleEventPublisher publisher;

    public AssignDefaultRoleHandler(RoleRepository repository, RoleEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    @Transactional
    public void handle(UUID userId) {
        UserRole role = new UserRole(userId, EnumSet.of(Role.USER));
        repository.save(role);

        log.info("Assigned default role '{}' to user {}", Role.USER, userId);

        RoleAssignedEvent event = new RoleAssignedEvent(userId, List.of(Role.USER.name()));
        publisher.publish(event);

        log.info("Published RoleAssignedEvent for user {}", userId);
    }

}
