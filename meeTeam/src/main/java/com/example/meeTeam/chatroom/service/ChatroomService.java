package com.example.meeTeam.chatroom.service;

import com.example.meeTeam.chatroom.Chatroom;
import com.example.meeTeam.chatroom.MemberChatroom;
import com.example.meeTeam.chatroom.ProjectRole;
import com.example.meeTeam.chatroom.dto.ChatroomDTO;
import com.example.meeTeam.chatroom.dto.ChatroomRequestDTO;
import com.example.meeTeam.chatroom.dto.ChatroomResponseDTO;
import com.example.meeTeam.member.Member;
import com.example.meeTeam.member.dto.MemberDetails;

import java.util.List;

public interface ChatroomService {
    ChatroomRequestDTO.chatroomCode createChatroomCode();
    ChatroomDTO createChatroom(ChatroomRequestDTO.chatroomCreate data, MemberDetails member);
    String enterChatroomNByCode(ChatroomRequestDTO.enterChatroom enterChatroom,MemberDetails member);
    ChatroomResponseDTO.enterSuccessMessage enterChatroom(MemberDetails member, ChatroomRequestDTO.chatroomId chatroomId);
    void saveChatrommInfo(Chatroom chatroom, MemberChatroom memberChatroom, ProjectRole role);

    //이거 chatroom으로 받은 다음에 members를 불러와야할 것 같음 memebers받아오는건 조금 힘들듯
    String calAvailableDate(ChatroomRequestDTO.chatroomId chatroomId);
    String calHalfwayPoint(ChatroomRequestDTO.chatroomId chatroomId);
    String selectTeamLeader(List<MemberChatroom> memberChatroomList);

    List<Member> getMemberByChatroomID(ChatroomDTO chatroomDTO);

    List<ChatroomDTO> getChatroomsByMember(Member member);
    List<ChatroomDTO> getAllChatrooms();
    ChatroomDTO getChatroomByCode(ChatroomRequestDTO.chatroomCode code);
    List<ChatroomResponseDTO.chatroomList> showChatroomListByMember(MemberDetails memberDetails);
    List<ChatroomResponseDTO.chatroomList> exitChatroom(MemberDetails memberDetails, ChatroomRequestDTO.chatroomId chatroomId);
    Member getTeamLeaderByChatroom(ChatroomDTO chatroomDTO);
    Chatroom getChatroomByRoomID(ChatroomRequestDTO.chatroomId chatroomid);

    ProjectRole getRoleByChatroomAndMember(Member memeber, ChatroomDTO chatroomDTO);
}