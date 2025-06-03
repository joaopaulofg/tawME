package com.tawme.chatservice.repository;

import com.tawme.chatservice.entity.UserChat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserChatRepository extends JpaRepository<UserChat, UUID> {

    List<UserChat> findByUserID(UUID userID);
}
