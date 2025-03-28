package com.poziomk3.stats_service.infrastructure.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatsEntity {

    @Id
    private Long id; // Use Long, not UUID

    private int totalRegistrations;
}
