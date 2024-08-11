package com.example.meeTeam.chatroom;

import com.example.meeTeam.member.Member;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ChatroomService {
    ChatroomDTO.chatroomCode createChatroomCode();
    ChatroomDTO createChatroom(ChatroomDTO.chatroomCreate data);
    String enterChatroom(ChatroomDTO.enterChatroom enterChatroom);
    void saveChatrommInfo(Chatroom chatroom, MemberChatroom memberChatroom, ProjectRole role);

    //이거 chatroom으로 받은 다음에 members를 불러와야할 것 같음 memebers받아오는건 조금 힘들듯
    String calAvailableDate(ChatroomDTO chatroom);
    String calHalfwayPoint(ChatroomDTO chatroom);
    String selectTeamLeader(List<MemberChatroom> memberChatroomList);

    List<Member> getMemberByChatroomID(ChatroomDTO chatroomDTO);
    List<ChatroomDTO> getChatroomsByMember(Member member);
    List<ChatroomDTO> getAllChatrooms();
    ChatroomDTO getChatroomByCode(ChatroomDTO.chatroomCode code);

    void test(ChatroomDTO.enterChatroom enterChatroom);
}