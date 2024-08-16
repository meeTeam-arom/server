package com.example.meeTeam.schedules;

import com.example.meeTeam.chatroom.Chatroom;
import com.example.meeTeam.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByChatroom(Chatroom chatroom);
}
