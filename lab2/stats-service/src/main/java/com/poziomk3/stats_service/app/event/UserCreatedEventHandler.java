package com.poziomk3.stats_service.app.event;

import com.poziomk3.dto.UserCreatedEvent;
import com.poziomk3.stats_service.domain.model.RegistrationStats;
import com.poziomk3.stats_service.domain.repository.StatsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserCreatedEventHandler {

    private final StatsRepository statsRepository;

    public UserCreatedEventHandler(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    @Transactional
    public void handle(UserCreatedEvent event) {
        RegistrationStats stats = statsRepository.getStats();
        stats.increment();
        statsRepository.save(stats);
    }
}
