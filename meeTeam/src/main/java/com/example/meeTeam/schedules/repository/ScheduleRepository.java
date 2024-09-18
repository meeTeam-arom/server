package com.example.meeTeam.schedules.repository;

import com.example.meeTeam.chatroom.Chatroom;
import com.example.meeTeam.schedules.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByChatroom(Chatroom chatroom);
}
