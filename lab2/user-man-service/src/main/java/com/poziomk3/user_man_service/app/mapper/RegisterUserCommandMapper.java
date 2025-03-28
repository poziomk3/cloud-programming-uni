package com.poziomk3.user_man_service.app.mapper;


import com.poziomk3.dto.UserRegisteredEvent;
import com.poziomk3.user_man_service.app.command.RegisterUserCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterUserCommandMapper {
    RegisterUserCommand toCommand(UserRegisteredEvent event);
}