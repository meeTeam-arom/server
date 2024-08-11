package com.example.meeTeam.chatroom;


import com.example.meeTeam.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {
    List<Chatroom> findByCode(String code);
}
