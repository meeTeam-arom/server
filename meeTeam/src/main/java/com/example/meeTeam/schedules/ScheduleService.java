package com.example.meeTeam.schedules;

import com.example.meeTeam.chatroom.dto.ChatroomDTO;
import com.example.meeTeam.chatroom.dto.ChatroomRequestDTO;
import com.example.meeTeam.member.Member;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDTO.listSchedule saveSchedule(ScheduleRequestDTO.saveSchedule scheduleDTO);
    List<ScheduleResponseDTO.listSchedule> getAllSchedule();
    List<ScheduleResponseDTO.listSchedule> getScheduleByChatroom(ChatroomRequestDTO.chatroomId chatroomId);
}
