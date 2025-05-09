package ru.alex9043.wrtesttask.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.alex9043.wrtesttask.dto.user.CreateUserRequest;
import ru.alex9043.wrtesttask.dto.user.UpdateUserRequest;
import ru.alex9043.wrtesttask.dto.user.UserResponse;
import ru.alex9043.wrtesttask.model.User;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserMapper {
    User createToEntity(CreateUserRequest createUserRequest);

    UserResponse toResponse(User savedUser);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromRequest(UpdateUserRequest request, @MappingTarget User user);
}
