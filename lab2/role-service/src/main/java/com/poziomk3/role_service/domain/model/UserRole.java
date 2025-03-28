package com.poziomk3.role_service.domain.model;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
public class UserRole {

    private UUID userId;
    private Set<Role> roles = new HashSet<>();

    public  UserRole() {
        // for MapStruct (and maybe JPA)
    }

    public UserRole(UUID userId) {
        this.userId = userId;
    }

    public UserRole(UUID userId, Set<Role> roles) {
        this.userId = userId;
        this.roles = roles;
    }

    public void assignRole(Role role) {
        this.roles.add(role);
    }

    public boolean hasRole(Role role) {
        return roles.contains(role);
    }
}
