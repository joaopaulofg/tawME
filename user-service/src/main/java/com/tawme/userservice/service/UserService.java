package com.tawme.userservice.service;

import com.tawme.userservice.dto.UserRequest;
import com.tawme.userservice.dto.UserResponse;
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
        return null;
    }

}
