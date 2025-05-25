package com.tawme.userservice.service;

import com.tawme.userservice.dto.UserRequest;
import com.tawme.userservice.dto.UserResponse;
import com.tawme.userservice.entity.User;
import com.tawme.userservice.mapper.UserMapper;
import com.tawme.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserRequest userRequest) {
        if (this.userRepository.findByPhoneNumber(userRequest.phoneNumber()) != null) return null;
        User newUser = UserMapper.INSTANCE.convertUserRequestToUser(userRequest);
        String ecryptedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(ecryptedPassword);
        userRepository.save(newUser);
        return UserMapper.INSTANCE.convertUserToUserResponse(newUser);
    }

}
