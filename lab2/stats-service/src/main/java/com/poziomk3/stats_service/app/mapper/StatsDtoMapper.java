package com.poziomk3.stats_service.app.mapper;

import com.poziomk3.dto.TotalRegistrationsResponse;
import com.poziomk3.stats_service.domain.model.RegistrationStats;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface StatsDtoMapper {

    @Mapping(source = "totalRegistrations", target = "total")
    TotalRegistrationsResponse toDto(RegistrationStats stats);
}
