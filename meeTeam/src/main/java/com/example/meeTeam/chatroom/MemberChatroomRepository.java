package com.example.meeTeam.chatroom;

import com.example.meeTeam.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberChatroomRepository extends JpaRepository<MemberChatroom, Long> {

    /*
    @Query(value = "SELECT u FROM MemberChatroom u WHERE u.member = :#{targetMember}", nativeQuery = true)
    List<MemberChatroom> findChatroomByMember(@Param("targetMember") Member targetMember);

    @Query(value = "SELECT u FROM MemberChatroom u WHERE u.chatroom = :#{targetChatroom}", nativeQuery = true)
    List<MemberChatroom> findMemberByChatroom(@Param("targetMember") ChatroomDTO targetChatroom);
     */

    List<MemberChatroom> findByMember(Member member);
    List<MemberChatroom> findByChatroom(Chatroom chatroom);
}