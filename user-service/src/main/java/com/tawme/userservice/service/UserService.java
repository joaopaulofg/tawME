package com.tawme.userservice.service;

import com.tawme.userservice.dto.UserRequest;
import com.tawme.userservice.dto.UserResponse;
import com.tawme.userservice.entity.User;
import com.tawme.userservice.mapper.UserMapper;
import com.tawme.userservice.repository.UserRepository;
import com.tawme.userservice.security.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    public UserResponse createUser(UserRequest userRequest) {
        User newUser = UserMapper.INSTANCE.convertUserRequestToUser(userRequest);
        String ecryptedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(ecryptedPassword);
        userRepository.save(newUser);
        return UserMapper.INSTANCE.convertUserToUserResponse(newUser);
    }

    public String login(String phoneNumber, String password) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(phoneNumber, password);
            var auth = authenticationManager.authenticate(usernamePassword);

            var user = (User) auth.getPrincipal();

            return tokenService.generateToken(user.getId(), user.getPhoneNumber());

        } catch (RuntimeException e) {
            return "Falha na autenticação: " + e.getMessage();
        }
    }



}
