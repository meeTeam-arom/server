package com.example.meeTeam.schedules.service;

import com.example.meeTeam.chatroom.dto.ChatroomRequestDTO;
import com.example.meeTeam.schedules.dto.ScheduleRequestDTO;
import com.example.meeTeam.schedules.dto.ScheduleResponseDTO;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDTO.listSchedule saveSchedule(ScheduleRequestDTO.saveSchedule scheduleDTO);
    List<ScheduleResponseDTO.listSchedule> getAllSchedule();
    List<ScheduleResponseDTO.listSchedule> getScheduleByChatroom(ChatroomRequestDTO.ChatroomId chatroomId);
}
