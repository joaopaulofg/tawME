package com.tawme.chatservice.controller;

import com.tawme.chatservice.entity.Chat;
import com.tawme.chatservice.security.AuthUtil;
import com.tawme.chatservice.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @GetMapping
    public ResponseEntity<List<Chat>> getAllChats() {
        UUID userId = AuthUtil.getCurrentUserId();

        List<Chat> chats = chatService.getAllChatsForUser(userId);
        return ResponseEntity.ok(chats);
    }
}
