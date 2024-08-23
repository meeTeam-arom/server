package com.example.meeTeam.schedules;

import com.example.meeTeam.chatroom.dto.ChatroomRequestDTO;
import com.example.meeTeam.global.exception.BaseResponse;
import com.example.meeTeam.member.dto.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    @Autowired
    private final ScheduleService scheduleService;

    @PostMapping("/schedule/create")
    public BaseResponse<?> createChatroom(@AuthenticationPrincipal MemberDetails member, ScheduleRequestDTO.saveSchedule scheduleDTO){
        return BaseResponse.onSuccess(scheduleService.saveSchedule(scheduleDTO));
    }

    @PostMapping("/schedule/showlist")
    public BaseResponse<?> createChatroom(@AuthenticationPrincipal MemberDetails member, ChatroomRequestDTO.chatroomId chatroomId){
        return BaseResponse.onSuccess(scheduleService.getScheduleByChatroom(chatroomId));
    }



}
