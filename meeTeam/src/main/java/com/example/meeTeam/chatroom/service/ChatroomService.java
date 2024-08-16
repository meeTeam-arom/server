package com.example.meeTeam.chatroom.service;

import com.example.meeTeam.chatroom.Chatroom;
import com.example.meeTeam.chatroom.MemberChatroom;
import com.example.meeTeam.chatroom.ProjectRole;
import com.example.meeTeam.chatroom.dto.ChatroomDTO;
import com.example.meeTeam.member.Member;
import com.example.meeTeam.member.dto.MemberDetails;

import java.util.List;

public interface ChatroomService {
    ChatroomDTO.chatroomCode createChatroomCode();
    ChatroomDTO createChatroom(ChatroomDTO.chatroomCreate data, MemberDetails member);
    String enterChatroomNByCode(ChatroomDTO.enterChatroom enterChatroom,MemberDetails member);
    ChatroomDTO.enterSuccessMessage enterChatroom(MemberDetails member, ChatroomDTO.chatroomId chatroomId);
    void saveChatrommInfo(Chatroom chatroom, MemberChatroom memberChatroom, ProjectRole role);

    //이거 chatroom으로 받은 다음에 members를 불러와야할 것 같음 memebers받아오는건 조금 힘들듯
    String calAvailableDate(ChatroomDTO chatroom);
    String calHalfwayPoint(ChatroomDTO chatroom);
    String selectTeamLeader(List<MemberChatroom> memberChatroomList);

    List<Member> getMemberByChatroomID(ChatroomDTO chatroomDTO);
    List<ChatroomDTO> getChatroomsByMember(Member member);
    List<ChatroomDTO> getAllChatrooms();
    ChatroomDTO getChatroomByCode(ChatroomDTO.chatroomCode code);
    List<ChatroomDTO.chatroomList> showChatroomListByMember(MemberDetails memberDetails);
    String exitChatroom(ChatroomDTO chatroom);
    Member getTeamLeaderByChatroom(ChatroomDTO chatroomDTO);

    //익명으로 요청
    //역할작성 ,  일정 생성
    // 방 종료시 ( 조원 평가하기, 기여도, 방은 사라져야함-> 방 상태 확인하는거 추가하기)

    void test(ChatroomDTO.enterChatroom enterChatroom);
}