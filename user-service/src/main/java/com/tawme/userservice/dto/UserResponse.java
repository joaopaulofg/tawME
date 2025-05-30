package com.tawme.userservice.dto;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String phoneNumber
) {
}
