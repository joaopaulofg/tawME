package com.tawme.userservice.controller;

import com.tawme.userservice.dto.LoginRequest;
import com.tawme.userservice.dto.LoginResponseDTO;
import com.tawme.userservice.dto.UserRequest;
import com.tawme.userservice.dto.UserResponse;
import com.tawme.userservice.mapper.UserMapper;
import com.tawme.userservice.repository.UserRepository;
import com.tawme.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final UserRepository repository;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        UserResponse newUser = userService.createUser(userRequest);
        if (newUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(newUser,  HttpStatus.CREATED);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequest body) {
        var user = repository.findByPhoneNumber(body.phoneNumber())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = userService.login(user.getPhoneNumber(), body.password());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

}
