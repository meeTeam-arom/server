package com.example.meeTeam.chatroom.controller;

import com.example.meeTeam.chatroom.service.ChatroomService;
import com.example.meeTeam.chatroom.dto.ChatroomDTO;
import com.example.meeTeam.global.exception.BaseResponse;
import com.example.meeTeam.member.Member;
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
    public BaseResponse<?> createChatroom(@AuthenticationPrincipal MemberDetails member, @RequestBody ChatroomDTO.chatroomCreate chatroomCreate){
        return BaseResponse.onSuccess(chatroomService.createChatroom(chatroomCreate,member).getCode());
    }

    //방입장 하기
    @PostMapping("/room/enter")
    public BaseResponse<?> enterChatroomByCode(@AuthenticationPrincipal MemberDetails member,@RequestBody ChatroomDTO.enterChatroom enterChatroom){
        return BaseResponse.onSuccess(chatroomService.enterChatroomNByCode(enterChatroom,member));
    }

    @PostMapping("/room/exit")
    public BaseResponse<?> exitChatroom(@AuthenticationPrincipal MemberDetails member,@RequestBody ChatroomDTO chatroom){
        return BaseResponse.onSuccess(chatroomService.exitChatroom(chatroom));
    }

    @GetMapping("/room/enter")
    public BaseResponse<?> enterChatroom(@AuthenticationPrincipal MemberDetails member,@RequestBody ChatroomDTO.chatroomId chatroomId){
        return BaseResponse.onSuccess(chatroomService.enterChatroom(member,chatroomId));
    }

    //중간 지점 계산
    @GetMapping("/room/HalfwayPoint/")
    public BaseResponse<?> calHalfwayPoint(@AuthenticationPrincipal MemberDetails memberDetails, @RequestBody ChatroomDTO chatroom){
        return BaseResponse.onSuccess(chatroomService.calHalfwayPoint(chatroom));
    }

    //회의가능한 날짜 계산
    @GetMapping("/room/availableDate/")
    public BaseResponse<?> calAvailableDate(@AuthenticationPrincipal MemberDetails memberDetails, @RequestBody ChatroomDTO chatroom){
        return BaseResponse.onSuccess(chatroomService.calAvailableDate(chatroom));
    }

    //멤버별 사용가능한 방 목록 리스트
    @GetMapping("/room/showRoomList")
    public BaseResponse<?> showRoomList(@AuthenticationPrincipal MemberDetails memberDetails){
        return BaseResponse.onSuccess(chatroomService.showChatroomListByMember(memberDetails));
    }


//    ==================  밑에는 필요없는거

    @PostMapping("/room/test")
    public BaseResponse<?> test(@RequestBody ChatroomDTO.enterChatroom enterChatroom){
        chatroomService.test(enterChatroom);
        return BaseResponse.onSuccess("test success");
    }


}
