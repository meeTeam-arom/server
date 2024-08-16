package com.example.meeTeam.schedules;

import com.example.meeTeam.chatroom.Chatroom;
import com.example.meeTeam.chatroom.dto.ChatroomDTO;
import com.example.meeTeam.global.handler.MyExceptionHandler;
import com.example.meeTeam.member.Member;
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

    public void saveSchedule(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule(scheduleDTO.getId(),scheduleDTO.getChatroom(),scheduleDTO.getTitle(),scheduleDTO.getContent(),scheduleDTO.isStatus());
        scheduleRepository.save(schedule);
    }

    public List<ScheduleDTO> getAllSchedule(){
        List<Schedule> scheduleList = Optional.ofNullable(scheduleRepository.findAll())
                .orElseGet(() -> new ArrayList<>());

        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        for(Schedule schedule : scheduleList){
            scheduleDTOList.add(ScheduleDTO.toDTO(schedule));
        }

        return scheduleDTOList;
    }

    public List<ScheduleDTO> getScheduleByChatroom(ChatroomDTO chatroomDTO){
        List<Schedule> scheduleList = Optional.ofNullable(scheduleRepository.findByChatroom(Chatroom.toEntity(chatroomDTO)))
                .orElseGet(() -> new ArrayList<>());

        if(scheduleList.isEmpty()){
            throw new MyExceptionHandler(SCHEDULE_NOT_FOUND);
        }

        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        for(Schedule schedule : scheduleList){
            scheduleDTOList.add(ScheduleDTO.toDTO(schedule));
        }

        return scheduleDTOList;
    }


}
