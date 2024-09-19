package com.example.meeTeam.chatroom.controller;

import com.example.meeTeam.chatroom.dto.ChatRequestDTO;
import com.example.meeTeam.chatroom.dto.ChatroomRequestDTO;
import com.example.meeTeam.chatroom.service.ChatroomService;
import com.example.meeTeam.global.exception.BaseResponse;
import com.example.meeTeam.member.dto.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChatroomController {

    @Autowired
    private final ChatroomService chatroomService;

    //방만들기
    @PostMapping("/room/create")
    public BaseResponse<?> createChatroom(@AuthenticationPrincipal MemberDetails member, @RequestBody ChatroomRequestDTO.ChatroomCreate chatroomCreate){
        return BaseResponse.onSuccess(chatroomService.createChatroom(chatroomCreate,member).getCode());
    }

    //방입장 하기
    @PostMapping("/room/enterCode")
    public BaseResponse<?> enterChatroomByCode(@AuthenticationPrincipal MemberDetails member,@RequestBody ChatroomRequestDTO.EnterChatroom enterChatroom){
        return BaseResponse.onSuccess(chatroomService.enterChatroomNByCode(enterChatroom,member));
    }

    @PostMapping("/room/exit")
    public BaseResponse<?> exitChatroom(@AuthenticationPrincipal MemberDetails memberDetails,@RequestBody ChatroomRequestDTO.ChatroomId chatroomId){
        return BaseResponse.onSuccess(chatroomService.exitChatroom(memberDetails,chatroomId));
    }

    @GetMapping("/room/enter")
    public BaseResponse<?> enterChatroom(@AuthenticationPrincipal MemberDetails member,@RequestBody ChatroomRequestDTO.ChatroomId chatroomId){
        return BaseResponse.onSuccess(chatroomService.enterChatroom(member,chatroomId));
    }

    //중간 지점 계산
    @GetMapping("/room/HalfwayPoint")
    public BaseResponse<?> calHalfwayPoint(@AuthenticationPrincipal MemberDetails memberDetails, @RequestBody ChatroomRequestDTO.ChatroomId chatroomId){
        return BaseResponse.onSuccess(chatroomService.calHalfwayPoint(chatroomId));
    }

    //회의가능한 날짜 계산
    @GetMapping("/room/availableDate")
    public BaseResponse<?> calAvailableDate(@AuthenticationPrincipal MemberDetails memberDetails, @RequestBody ChatroomRequestDTO.ChatroomId chatroomId){
        return BaseResponse.onSuccess(chatroomService.calAvailableDate(chatroomId));
    }

    //멤버별 사용가능한 방 목록 리스트
    @GetMapping("/room/showRoomList")
    public BaseResponse<?> showRoomList(@AuthenticationPrincipal MemberDetails memberDetails){
        return BaseResponse.onSuccess(chatroomService.showChatroomListByMember(memberDetails));
    }

    @PostMapping("/room/anonymousMessage")
    public BaseResponse<?> sendAnonymousMessage(@AuthenticationPrincipal MemberDetails memberDetails, ChatRequestDTO.AnonymousMesssage anonnymousMessage){
        return BaseResponse.onSuccess(chatroomService.sendAnonymousMessage(anonnymousMessage));
    }

}
