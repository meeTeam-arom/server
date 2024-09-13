package com.example.meeTeam.chatroom.repository;


import com.example.meeTeam.chatroom.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {
    List<Chatroom> findByCode(String code);
    Optional<Chatroom> findById(Long id);
}
