package com.tawme.chatservice.repository;

import com.tawme.chatservice.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepositoy extends JpaRepository<Message, UUID> {

    List<Message> findByChatIdOrderBySentAtAsc(UUID chatID);

}
