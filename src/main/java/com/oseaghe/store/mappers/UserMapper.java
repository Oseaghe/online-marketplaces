package com.oseaghe.store.mappers;

import com.oseaghe.store.dtos.RegisterUserRequest;
import com.oseaghe.store.dtos.UpdateUserRequest;
import com.oseaghe.store.dtos.UserDto;
import com.oseaghe.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
    void update(UpdateUserRequest request, @MappingTarget User user);
}
