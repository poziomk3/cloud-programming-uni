package com.poziomk3.role_service.app.command;

import java.util.UUID;

public record AssignDefaultRoleCommand(UUID userId, String email) {
}