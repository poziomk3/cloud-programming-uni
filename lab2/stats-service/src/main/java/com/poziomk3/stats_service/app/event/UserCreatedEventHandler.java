package com.poziomk3.stats_service.app.event;

import com.poziomk3.dto.UserCreatedEvent;
import com.poziomk3.stats_service.domain.model.RegistrationStats;
import com.poziomk3.stats_service.domain.repository.StatsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserCreatedEventHandler {

    private final StatsRepository statsRepository;

    public UserCreatedEventHandler(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    @Transactional
    public void handle(UserCreatedEvent event) {
        log.info("Handling UserCreatedEvent for userId: {}", event.getUserId());

        RegistrationStats stats = statsRepository.getStats();
        stats.increment();
        statsRepository.save(stats);

        log.info("Updated registration stats after user creation for userId: {}", event.getUserId());
    }
}
