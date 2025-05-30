package com.tawme.chatservice.security;

import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public class AuthUtil {
    public static UUID getCurrentUserId() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal() == null) {
            throw new RuntimeException("Usuário não autenticado.");
        }
        return UUID.fromString(auth.getPrincipal().toString());
    }
}
