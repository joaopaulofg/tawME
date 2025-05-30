package com.tawme.chatservice.controller;

import com.tawme.chatservice.entity.User;
import com.tawme.chatservice.repository.UserRepository;
import com.tawme.chatservice.security.AuthUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/teste")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<User> me() {
        UUID userId = AuthUtil.getCurrentUserId();
        User user = userRepository.findById(userId).orElseThrow();
        return ResponseEntity.ok(user);
    }
}
