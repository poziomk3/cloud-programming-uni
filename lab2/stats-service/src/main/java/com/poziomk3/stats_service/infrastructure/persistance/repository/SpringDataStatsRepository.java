package com.poziomk3.stats_service.infrastructure.persistance.repository;


import com.poziomk3.stats_service.infrastructure.persistance.entity.StatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.channels.FileChannel;

public interface SpringDataStatsRepository extends JpaRepository<StatsEntity, Long> {
}
