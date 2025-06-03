package com.tawme.chatservice.service;

import com.tawme.chatservice.entity.Chat;
import com.tawme.chatservice.entity.UserChat;
import com.tawme.chatservice.repository.ChatRepository;
import com.tawme.chatservice.repository.UserChatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserChatRepository userChatRepository;

    public List<Chat> getAllChatsForUser(UUID userID){
        List<UserChat> userChat = userChatRepository.findByUserID(userID);
        List<UUID> chatIDs = userChat.stream().map(UserChat::getChatID).toList();

        return chatRepository.findAllById(chatIDs);
    }

}
