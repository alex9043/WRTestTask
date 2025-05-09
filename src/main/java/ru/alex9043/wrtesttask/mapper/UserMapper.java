package ru.alex9043.wrtesttask.mapper;

import org.mapstruct.Mapper;
import ru.alex9043.wrtesttask.dto.CreateUserRequest;
import ru.alex9043.wrtesttask.dto.UserResponse;
import ru.alex9043.wrtesttask.model.User;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserMapper {
    User createToEntity(CreateUserRequest createUserRequest);

    UserResponse toResponse(User savedUser);
}
