package com.poziomk3.stats_service.domain.repository;

import com.poziomk3.stats_service.domain.model.RegistrationStats;

public interface StatsRepository {

    RegistrationStats getStats();

    void save(RegistrationStats stats);
}
