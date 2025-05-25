package com.tawme.userservice.dto;

public record UserRequest(
        String name,
        String phoneNumber,
        String password
) {
}
