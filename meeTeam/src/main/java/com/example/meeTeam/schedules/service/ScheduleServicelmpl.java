package com.example.meeTeam.schedules.service;

import com.example.meeTeam.chatroom.Chatroom;
import com.example.meeTeam.chatroom.dto.ChatroomDTO;
import com.example.meeTeam.chatroom.dto.ChatroomRequestDTO;
import com.example.meeTeam.chatroom.service.ChatroomService;
import com.example.meeTeam.global.handler.MyExceptionHandler;
import com.example.meeTeam.schedules.Schedule;
import com.example.meeTeam.schedules.repository.ScheduleRepository;
import com.example.meeTeam.schedules.dto.ScheduleRequestDTO;
import com.example.meeTeam.schedules.dto.ScheduleResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.meeTeam.global.exception.codes.ErrorCode.SCHEDULE_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ScheduleServicelmpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;
    private final ChatroomService chatroomService;

    public ScheduleResponseDTO.listSchedule saveSchedule(ScheduleRequestDTO.saveSchedule scheduleDTO){
        Schedule schedule = new Schedule(scheduleDTO.chatroom(),scheduleDTO.title(),scheduleDTO.content(),true, scheduleDTO.deadline());
        scheduleRepository.save(schedule);
        return new ScheduleResponseDTO.listSchedule(schedule.getTitle(), schedule.getContent(), schedule.getDeadline());
    }

    public List<ScheduleResponseDTO.listSchedule> getAllSchedule(){
        List<Schedule> scheduleList = Optional.ofNullable(scheduleRepository.findAll())
                .orElseGet(() -> new ArrayList<>());

        List<ScheduleResponseDTO.listSchedule> scheduleDTOList = new ArrayList<>();
        for(Schedule schedule : scheduleList){
            scheduleDTOList.add(new ScheduleResponseDTO.listSchedule(schedule.getTitle(),schedule.getContent(),schedule.getDeadline()));
        }

        return scheduleDTOList;
    }

    public List<ScheduleResponseDTO.listSchedule> getScheduleByChatroom(ChatroomRequestDTO.ChatroomId chatroomId){
        Chatroom chatroom = chatroomService.getChatroomByRoomID(chatroomId.id());
        List<Schedule> scheduleList = Optional.ofNullable(scheduleRepository.findByChatroom(Chatroom.toEntity(ChatroomDTO.toDTO(chatroom))))
                .orElseGet(() -> new ArrayList<>());

        if(scheduleList.isEmpty()){
            throw new MyExceptionHandler(SCHEDULE_NOT_FOUND);
        }

        List<ScheduleResponseDTO.listSchedule> scheduleDTOList = new ArrayList<>();
        for(Schedule schedule : scheduleList){
            scheduleDTOList.add(new ScheduleResponseDTO.listSchedule(schedule.getTitle(),schedule.getContent(),schedule.getDeadline()));
        }

        return scheduleDTOList;
    }


}
