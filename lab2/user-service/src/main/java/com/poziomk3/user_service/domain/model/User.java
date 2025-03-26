package com.poziomk3.user_service.domain.model;

import java.time.OffsetDateTime;
import java.util.UUID;

public class User {
    private final UUID id;
    private final String email;
    private final OffsetDateTime createdAt;
    private final String passwordHash;

    public User(UUID id, String email, String passwordHash, OffsetDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
    }

    public static User register(String email, String passwordHash) {
        return new User(UUID.randomUUID(), email, passwordHash, OffsetDateTime.now());
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}
