package com.poziomk3.stats_service.infrastructure.persistance.mapper;

import com.poziomk3.stats_service.domain.model.RegistrationStats;
import com.poziomk3.stats_service.infrastructure.persistance.entity.StatsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StatsMapper {

    @Mapping(target = "totalRegistrations", source = "totalRegistrations")
    RegistrationStats toDomain(StatsEntity entity);

    @Mapping(target = "id", ignore = true)
    StatsEntity toEntity(RegistrationStats stats);
}