package com.example.meeTeam.chatroom.service;

import com.example.meeTeam.chatroom.Chatroom;
import com.example.meeTeam.chatroom.MemberChatroom;
import com.example.meeTeam.chatroom.ProjectRole;
import com.example.meeTeam.chatroom.dto.*;
import com.example.meeTeam.member.model.Member;
import com.example.meeTeam.member.dto.MemberDetails;

import java.util.List;

public interface ChatroomService {
    ChatroomRequestDTO.ChatroomCode createChatroomCode();
    ChatroomDTO createChatroom(ChatroomRequestDTO.ChatroomCreate data, MemberDetails member);
    String enterChatroomNByCode(ChatroomRequestDTO.EnterChatroom enterChatroom,MemberDetails member);
    ChatroomResponseDTO.EnterSuccessMessage enterChatroom(MemberDetails member, ChatroomRequestDTO.ChatroomId chatroomId);
    void saveChatrommInfo(Chatroom chatroom, MemberChatroom memberChatroom, ProjectRole role);

    //이거 chatroom으로 받은 다음에 members를 불러와야할 것 같음 memebers받아오는건 조금 힘들듯
    String calAvailableDate(ChatroomRequestDTO.ChatroomId chatroomId);
    String calHalfwayPoint(ChatroomRequestDTO.ChatroomId chatroomId);
    String selectTeamLeader(List<MemberChatroom> memberChatroomList);

    List<Member> getMemberByChatroomID(ChatroomDTO chatroomDTO);

    List<ChatroomDTO> getChatroomsByMember(Member member);
    List<ChatroomDTO> getAllChatrooms();
    ChatroomDTO getChatroomByCode(ChatroomRequestDTO.ChatroomCode code);
    List<ChatroomResponseDTO.ChatroomList> showChatroomListByMember(MemberDetails memberDetails);
    List<ChatroomResponseDTO.ChatroomList> exitChatroom(MemberDetails memberDetails, ChatroomRequestDTO.ChatroomId chatroomId);
    Member getTeamLeaderByChatroom(ChatroomDTO chatroomDTO);
    Chatroom getChatroomByRoomID(Long chatroomid);

    ProjectRole getRoleByChatroomAndMember(Member memeber, ChatroomDTO chatroomDTO);
    ChatResponseDTO.AnonymousMessage sendAnonymousMessage(ChatRequestDTO.AnonymousMesssage anonnymousMessage);
}