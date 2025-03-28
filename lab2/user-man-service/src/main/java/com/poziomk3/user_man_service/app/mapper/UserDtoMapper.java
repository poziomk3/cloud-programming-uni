package com.poziomk3.user_man_service.app.mapper;

import com.poziomk3.dto.FullUserDto;
import com.poziomk3.user_man_service.domain.model.User;
import org.mapstruct.Mapper;
import org.openapitools.jackson.nullable.JsonNullable;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    default <T> JsonNullable<T> wrap(T value) {
        return JsonNullable.of(value);
    }
    FullUserDto toDto(User user);
}
