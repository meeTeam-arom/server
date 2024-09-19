package com.example.meeTeam.chatroom.repository;

import com.example.meeTeam.chatroom.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {

}
