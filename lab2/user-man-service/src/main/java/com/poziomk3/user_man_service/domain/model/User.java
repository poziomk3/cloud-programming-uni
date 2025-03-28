package com.poziomk3.user_man_service.domain.model;

import java.time.OffsetDateTime;
import java.util.UUID;

public record User(UUID userId, String email, String password, String firstName, String lastName,
                   OffsetDateTime createdAt) {
}
