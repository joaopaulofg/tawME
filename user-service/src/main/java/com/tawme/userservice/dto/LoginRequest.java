package com.tawme.userservice.dto;

public record LoginRequest(
        String phoneNumber,
        String password
) {
}
