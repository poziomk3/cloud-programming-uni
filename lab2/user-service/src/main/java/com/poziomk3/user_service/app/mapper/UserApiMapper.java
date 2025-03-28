package com.poziomk3.user_service.app.mapper;

import com.poziomk3.dto.MinimalUserDto;
import com.poziomk3.dto.RegisterUserRequest;
import com.poziomk3.user_service.app.command.RegisterUserCommand;
import com.poziomk3.user_service.domain.model.RegisteredUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserApiMapper {

    RegisterUserCommand toCommand(RegisterUserRequest request);

    MinimalUserDto toDto(RegisteredUser user);
}
