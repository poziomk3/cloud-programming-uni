package com.poziomk3.user_service.domain.model;

import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

public record RegisteredUser(UUID userId, String email, OffsetDateTime createdAt) {

}

