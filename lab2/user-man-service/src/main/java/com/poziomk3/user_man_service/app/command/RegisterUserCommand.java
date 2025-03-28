package com.poziomk3.user_man_service.app.command;

import java.time.OffsetDateTime;
import java.util.UUID;

public record RegisterUserCommand(UUID userId, String email, String password, OffsetDateTime createdAt) {
}
