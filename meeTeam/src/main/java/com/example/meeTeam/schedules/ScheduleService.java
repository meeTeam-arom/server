package com.example.meeTeam.schedules;

import com.example.meeTeam.chatroom.dto.ChatroomDTO;
import com.example.meeTeam.member.Member;

import java.util.List;

public interface ScheduleService {
    void saveSchedule(ScheduleDTO scheduleDTO);
    List<ScheduleDTO> getAllSchedule();
    List<ScheduleDTO> getScheduleByChatroom(ChatroomDTO chatroomDTO);
}
