package com.tawme.chatservice.controller;

import com.tawme.chatservice.entity.Message;
import com.tawme.chatservice.security.AuthUtil;
import com.tawme.chatservice.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/{chatID}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable UUID chatID) {
        UUID userId = AuthUtil.getCurrentUserId();

        List<Message> messages = messageService.getMessagesForChat(userId, chatID);
        return ResponseEntity.ok(messages);
    }

}
