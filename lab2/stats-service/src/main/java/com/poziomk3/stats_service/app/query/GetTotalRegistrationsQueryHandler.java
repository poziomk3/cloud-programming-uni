package com.poziomk3.stats_service.app.query;

import com.poziomk3.dto.TotalRegistrationsResponse;
import com.poziomk3.stats_service.app.mapper.StatsDtoMapper;
import com.poziomk3.stats_service.domain.repository.StatsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GetTotalRegistrationsQueryHandler {

    private final StatsRepository repository;
    private final StatsDtoMapper mapper;

    public GetTotalRegistrationsQueryHandler(StatsRepository repository, StatsDtoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TotalRegistrationsResponse handle() {
        var stats = repository.getStats(); // returns RegistrationStats
        log.info("Got total register users number from db: {}", stats.getTotalRegistrations());
        return mapper.toDto(stats);        // map to DTO here ✅
    }
}
