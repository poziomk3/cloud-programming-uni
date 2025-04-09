package com.poziomk3.user_man_service.app.mapper;

import com.poziomk3.dto.FullUserDto;
import com.poziomk3.user_man_service.domain.model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    FullUserDto toDto(User user);

    User toEntity(FullUserDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(FullUserDto dto, @MappingTarget User user);
}
