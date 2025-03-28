package com.poziomk3.role_service.infrastructure.persistance.entity;

import com.poziomk3.role_service.domain.model.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
public class UserRoleEntity {

    @Id
    private UUID userId;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    // ✅ Required by JPA
    protected UserRoleEntity() {
    }

    // Optional constructor for convenience
    public UserRoleEntity(UUID userId, Set<Role> roles) {
        this.userId = userId;
        this.roles = roles;
    }
}
