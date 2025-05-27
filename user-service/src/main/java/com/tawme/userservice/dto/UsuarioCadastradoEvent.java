package com.tawme.userservice.dto;

import java.util.UUID;

public record UsuarioCadastradoEvent(
        UUID id,
        String name,
        String phoneNumber
) {
}
