package com.tawme.chatservice.service;

import com.tawme.chatservice.entity.Message;
import com.tawme.chatservice.repository.ChatRepository;
import com.tawme.chatservice.repository.MessageRepositoy;
import com.tawme.chatservice.repository.UserChatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepositoy messageRepositoy;
    private final UserChatRepository userChatRepository;

    public List<Message> getMessagesForChat(UUID userID, UUID chatID) {
        boolean isPaticipant = userChatRepository.existsByUserIdAndChatId(userID, chatID);

        if(!isPaticipant) {
            throw new RuntimeException("User is not paticipant");
        }

        return messageRepositoy.findByChatIdOrderBySentAtAsc(chatID);
    }


}
