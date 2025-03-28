package com.poziomk3.stats_service.infrastructure.persistance.repository;

import com.poziomk3.stats_service.domain.model.RegistrationStats;
import com.poziomk3.stats_service.domain.repository.StatsRepository;
import com.poziomk3.stats_service.infrastructure.persistance.entity.StatsEntity;
import com.poziomk3.stats_service.infrastructure.persistance.mapper.StatsMapper;
import org.springframework.stereotype.Repository;

@Repository
public class StatsRepositoryImpl implements StatsRepository {

    private final SpringDataStatsRepository jpaRepo;
    private final StatsMapper mapper;

    public StatsRepositoryImpl(SpringDataStatsRepository jpaRepo, StatsMapper mapper) {
        this.jpaRepo = jpaRepo;
        this.mapper = mapper;
    }

    @Override
    public RegistrationStats getStats() {
        return jpaRepo.findById(1L)
                .map(mapper::toDomain)
                .orElse(new RegistrationStats(0));
    }

    @Override
    public void save(RegistrationStats stats) {
        StatsEntity entity = mapper.toEntity(stats);
        entity.setId(1L); // Ensure ID is set since we're overwriting the singleton record
        jpaRepo.save(entity);
    }
}
