package com.poziomk3.user_service.infrastructure.persistance.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    public UUID id;
    public String email;
    public OffsetDateTime createdAt;
    public String passwordHash;
}