package com.tawme.userservice.mapper;

import com.tawme.userservice.dto.UserRequest;
import com.tawme.userservice.dto.UserResponse;
import com.tawme.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User convertUserRequestToUser(UserRequest userRequest);

    UserResponse convertUserToUserResponse(User user);
}
