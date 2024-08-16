package com.example.meeTeam.chatroom.service;

import com.example.meeTeam.chatroom.Chatroom;
import com.example.meeTeam.chatroom.MemberChatroom;
import com.example.meeTeam.chatroom.ProjectRole;
import com.example.meeTeam.chatroom.dto.ChatroomDTO;
import com.example.meeTeam.chatroom.repository.ChatroomRepository;
import com.example.meeTeam.chatroom.repository.MemberChatroomRepository;
import com.example.meeTeam.chatroom.repository.ProjectRoleRepository;
import com.example.meeTeam.global.handler.MyExceptionHandler;
import com.example.meeTeam.member.Member;
import com.example.meeTeam.member.dto.MemberDetails;
import com.example.meeTeam.member.dto.MemberRegisterRequestDto;
import com.example.meeTeam.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.example.meeTeam.global.exception.codes.ErrorCode.*;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ChatroomServicelmpl implements ChatroomService{

    private final ChatroomRepository chatroomRepository;
    private final MemberChatroomRepository memberChatroomRepository;
    private final ProjectRoleRepository projectRoleRepository;
    private final MemberRepository memberRepository;

   public  void test(ChatroomDTO.enterChatroom enterChatroom){
        System.out.println(enterChatroom.getCode());
        System.out.println(enterChatroom.isWantLeader());
    }

    public String calHalfwayPoint(ChatroomDTO chatroom){
       List<Member> members = getMemberByChatroomID(chatroom);
        int numberOfMembers = members.size();

        if(numberOfMembers != chatroom.getTotalMember()) throw new MyExceptionHandler(SHORT_NUMBER_PEOPLE);

        List<Double> latitudeList = new ArrayList<>();
        List<Double> longitudeList = new ArrayList<>();

        for(Member member : members){
            latitudeList.add(member.getLatitude());
            longitudeList.add(member.getLongitude());
        }

        Double sumOfLatitude=0.0;
        Double sumOfLongitude=0.0;

        for(int i=0;i<numberOfMembers;i++){
            sumOfLatitude += latitudeList.get(i);
            sumOfLongitude += longitudeList.get(i);
        }

        Double avgLatitude = sumOfLatitude/numberOfMembers;
        Double avgLongitude = sumOfLongitude/numberOfMembers;

        StringBuilder location = new StringBuilder();
        location.append(avgLatitude+","+avgLongitude);

        return location.toString();
    }


    public String enterChatroomNByCode(ChatroomDTO.enterChatroom enterChatroom,MemberDetails memberDetails){

        //코드 맞는지 확인
        ChatroomDTO.chatroomCode code = new ChatroomDTO.chatroomCode();
        code.setCode(enterChatroom.getCode());
        ChatroomDTO chatroomDTO = getChatroomByCode(code);

        if(chatroomDTO == null){
            //검색한 코드가 없을경우 예외 처리
            System.out.println("enter the code correctly");
            return "enter the code correctly";
        }

        Chatroom chatroom = Chatroom.toEntity(chatroomDTO);

        Optional<Member> member = memberRepository.findMemberByEmail(memberDetails.getUsername());
        if(!member.isPresent()){
            System.out.println("member not found");
        }else{
            //현재 들어가려는 방에 이미 가입되어 있는지 확인 해야함.
            List<MemberChatroom> membersByChatroomList = memberChatroomRepository.findByMember(member.get());
            for(MemberChatroom memberChatroom : membersByChatroomList){
                if(memberChatroom.getChatroom().getId()==chatroom.getId()){
                    throw new MyExceptionHandler(ALREADY_ENTER_ROOM);
                }
            }
        }

        //인원수 꽉 찬지 확인
        List<Member> members = getMemberByChatroomID(chatroomDTO);
        int numberOfTotalMember = chatroomDTO.getTotalMember();
        int numberOfMember = members.size();

        System.out.println(numberOfMember+" "+numberOfTotalMember);

        if(numberOfMember >= numberOfTotalMember){
            throw new MyExceptionHandler(OVER_NUMBER_PEOPLE);
        }else if(numberOfMember < numberOfTotalMember){

            MemberChatroom memberChatroom = new MemberChatroom(
                    chatroom,
                    member.get(),
                    null
            );

            ProjectRole projectRole = new ProjectRole(
                    ProjectRole.Role.FOLLOWER,
                    enterChatroom.isWantLeader(),
                    memberChatroom
            );

            saveChatrommInfo(chatroom,memberChatroom,projectRole);
            //인원수 꽉찰 경우 리더 정해야함
            if(numberOfMember+1==numberOfTotalMember){
                System.out.println("방 문 닫고 들어옴");
                selectTeamLeader(chatroomDTO.getMemberChatroomList());
            }
        }

        return "success";
    }

    public ChatroomDTO.enterSuccessMessage enterChatroom(MemberDetails memberDetails, ChatroomDTO.chatroomId chatroomId){
       //방에 들어가 있는지 확인이 필요하긴함.

       ChatroomDTO.enterSuccessMessage successMessage = new ChatroomDTO.enterSuccessMessage();

       Optional<Chatroom> chatroomById = chatroomRepository.findById(chatroomId.getId());
        if(!chatroomById.isPresent()) throw new MyExceptionHandler(ROOM_NOT_FOUND);

        Chatroom chatroom = chatroomById.get();
        ChatroomDTO chatroomDTO = ChatroomDTO.toDTO(chatroom);
        List<Member> members = getMemberByChatroomID(chatroomDTO);

        List<String> memberNames = new ArrayList<>();
        for(Member member : members){
            memberNames.add(member.getMemberName());
        }

        successMessage.setChatroomName(chatroom.getChatroomName());
        successMessage.setTotalMember(chatroom.getTotalMember());
        successMessage.setMembers(memberNames);

        Member teamLeader = getTeamLeaderByChatroom(chatroomDTO);
        if(teamLeader != null) successMessage.setLeaderName(teamLeader.getMemberName());

       return successMessage;
    }

    public Member getTeamLeaderByChatroom(ChatroomDTO chatroomDTO){
        Chatroom chatroom = Chatroom.toEntity(chatroomDTO);
        Member teamLeader = null;

        List<MemberChatroom> memberChatroomList = memberChatroomRepository.findByChatroom(chatroom);
        for(MemberChatroom memberChatroom : memberChatroomList){
            Optional<ProjectRole> tmpRole = projectRoleRepository.findByMemberChatroom(memberChatroom);
            if(tmpRole.isPresent()){
                if(tmpRole.get().getRole() == ProjectRole.Role.LEADER) teamLeader=memberChatroom.getMember();
            }
        }

        return teamLeader;
    }

    public ChatroomDTO getChatroomByCode(ChatroomDTO.chatroomCode code){

        System.out.println("여기까지는 들어와짐 == " + code);

        //여기가 안됨 왜 안될까??????????????????????????????????????????????????????????????????????????????? 8/8마지막 확인
        List<Chatroom> getChatroom = Optional.ofNullable(chatroomRepository.findByCode(code.getCode()))
                .orElseGet(() -> new ArrayList<>());

        System.out.println("getChatroom:"+getChatroom);

        if(getChatroom.isEmpty()){
            System.out.println("getChatroom is empty");
            return null;
        }

        ChatroomDTO chatroom = ChatroomDTO.toDTO(getChatroom.get(0));
        return chatroom;
    }

    public String calAvailableDate(ChatroomDTO chatroom){
       List<Member> members = getMemberByChatroomID(chatroom);

       if(members.size() != chatroom.getTotalMember()) throw new MyExceptionHandler(SHORT_NUMBER_PEOPLE);

        StringBuilder resultAvailableDate = new StringBuilder();
        List<String> availableDates = new ArrayList<>();
        int numberOfDigits = 48*7;

        //멤버들 날짜 가져옴.
        for(Member member : members){
            //멤버들 날짜 자릿수 확인해야함!
            availableDates.add(member.getAvailableDate());
        }

        //멤버들 날짜 48개씩 나누고 합들 구함.
        for(int i=0;i<numberOfDigits;i++){
            int tmpHap=0;
            for(String availableDate : availableDates){
                tmpHap += Integer.parseInt(availableDate.substring(i, i+1));
            }
            resultAvailableDate.append(tmpHap);
        }

        return resultAvailableDate.toString();
    }

    public String selectTeamLeader(List<MemberChatroom> memberChatroomList){

        System.out.println("enter SelectTeamLeader");

        List<ProjectRole> projectRoleList = new ArrayList<>();
        List<ProjectRole> wantLeaderList = new ArrayList<>();
        int numOfWantLeader = 0;
        int numOfMember = memberChatroomList.size();

        for(MemberChatroom memberChatroom : memberChatroomList){

            System.out.println(memberChatroom.getId());

            Optional<ProjectRole> tmpRole = projectRoleRepository.findByMemberChatroom(memberChatroom);
            if(tmpRole.isPresent()){
                projectRoleList.add(tmpRole.get());
                if(tmpRole.get().isWantLeader() == true){
                    numOfWantLeader++;
                    wantLeaderList.add(tmpRole.get());
                }
            }

        }

        if(numOfWantLeader == 0){
            //random으로 돌리기
            Random random = new Random();
            random.setSeed(System.currentTimeMillis());
            int randomNum = random.nextInt(numOfMember);
            projectRoleList.get(randomNum).setRole(ProjectRole.Role.LEADER);
            projectRoleRepository.save(projectRoleList.get(randomNum));

        }else{
            //원하는 사람 중에 점수 높은 사람으로 구하기
            if(numOfWantLeader==1){
                wantLeaderList.get(0).setRole(ProjectRole.Role.LEADER);
                projectRoleRepository.save(wantLeaderList.get(0));
            }else{
                //멤버 등수 정한 다음 그냥 leader로 바꿔주면 될듯
                int maxIndex=-1,nowIndex=0;
                double mannerScore = Double.MIN_VALUE;
                for(ProjectRole projectrole : wantLeaderList){
                    Member member = projectrole.getMemberChatroom().getMember();
                    if(member.getMemberMannerTemp()>mannerScore){
                        mannerScore=member.getMemberMannerTemp();
                        maxIndex=nowIndex;
                    }
                    nowIndex++;
                }
                wantLeaderList.get(maxIndex).setRole(ProjectRole.Role.LEADER);
                projectRoleRepository.save(wantLeaderList.get(maxIndex));
            }
        }
        return "success";
    }

    public void saveChatrommInfo(Chatroom chatroom, MemberChatroom memberChatroom, ProjectRole role){

        chatroomRepository.save(chatroom);
        chatroom.addMemberChatroom(memberChatroom);

        memberChatroomRepository.save(memberChatroom);
        memberChatroom.addProjectRole(role);

        projectRoleRepository.save(role);
    }

    public ChatroomDTO createChatroom(ChatroomDTO.chatroomCreate data, MemberDetails memberDetails){

        ChatroomDTO.chatroomCode chatroomCode = createChatroomCode();
        Optional<Member> member = memberRepository.findMemberByEmail(memberDetails.getUsername());

        if(!member.isPresent()){
            throw new MyExceptionHandler(EMAIL_NOT_FOUND);
        }

        Chatroom chatroom = new Chatroom(null,
                data.getChatroomName(),
                data.getTotoalMember(),
                chatroomCode.getCode(),
                new ArrayList<>(),
                new ArrayList<>(),
                true
        );

        MemberChatroom memberChatroom = new MemberChatroom(
                chatroom,
                member.get(),
            null
        );

        ProjectRole projectRole = new ProjectRole(
                ProjectRole.Role.FOLLOWER,
                data.isWantLeader(),
                memberChatroom
        );

        saveChatrommInfo(chatroom,memberChatroom,projectRole);

        ChatroomDTO chatroomDTO = ChatroomDTO.toDTO(chatroom);
        return chatroomDTO;
    }



    public boolean isVaildCode(ChatroomDTO.chatroomCode code){

        List<ChatroomDTO> allChatrooms = getAllChatrooms();
        if(!allChatrooms.isEmpty()){
            for(ChatroomDTO chatroom : allChatrooms){
                if(chatroom.getCode().equals(code.toString())) return false;
            }
        }
        return true;
    }

    public ChatroomDTO.chatroomCode createChatroomCode(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();
        String randomString="";

        ChatroomDTO.chatroomCode code = new ChatroomDTO.chatroomCode();

        do {
            randomString = random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            code.setCode(randomString);

        }while(!isVaildCode(code));

        return code;
    }

    public List<ChatroomDTO> getAllChatrooms(){
        List<Chatroom> getChatrooms = Optional.ofNullable(chatroomRepository.findAll())
                .orElseGet(() -> new ArrayList<>());

        List<ChatroomDTO> allChatrooms = new ArrayList<>();
        for(Chatroom chatroom : getChatrooms){
            allChatrooms.add(
                    ChatroomDTO.builder()
                    .id(chatroom.getId())
                    .chatroomName(chatroom.getChatroomName())
                    .totalMember(chatroom.getTotalMember())
                    .code(chatroom.getCode())
                    .memberChatroomList(chatroom.getMemberChatroomList())
                    .schedules(chatroom.getSchedules())
                    .build()
            );
        }
        return allChatrooms;
    }

    public List<ChatroomDTO> getChatroomsByMember(Member member){

        List<MemberChatroom> memberChatroomsByMember = Optional.ofNullable(memberChatroomRepository.findByMember(member))
                .orElseGet(() -> new ArrayList<>());

        if(memberChatroomsByMember.isEmpty()){
            throw new MyExceptionHandler(ROOM_NOT_FOUND);
        }

        List<ChatroomDTO> chatrooms = new ArrayList<>();

        for(MemberChatroom memberChatroom : memberChatroomsByMember){
            chatrooms.add(ChatroomDTO.toDTO(memberChatroom.getChatroom()));
        }

        return chatrooms;
    }


    public List<Member> getMemberByChatroomID(ChatroomDTO chatroomDTO) {
        //member값이 안가여와짐

        List<MemberChatroom> memberChatroomsByChatroom = Optional.ofNullable(memberChatroomRepository.findByChatroom(Chatroom.toEntity(chatroomDTO)))
                .orElseGet(() -> new ArrayList<>());


        if(memberChatroomsByChatroom.isEmpty()){
            throw new MyExceptionHandler(NOT_VALID_ERROR);
        }

        List<Member> members = new ArrayList<>();

        for (MemberChatroom memberChatroom : memberChatroomsByChatroom) {
            members.add(memberChatroom.getMember());
        }

        return members;
    }

    public String exitChatroom(ChatroomDTO chatroom){
        //조원평가하기, 방사라져야함 두개 필요함.
        //방 상태 불가로 만들기
        chatroom.setStatus(false);
        chatroomRepository.save(Chatroom.toEntity(chatroom));

        return "success";
    }

    public List<ChatroomDTO.chatroomList> showChatroomListByMember(MemberDetails memberDetails){
       Optional<Member> member = memberRepository.findMemberByEmail(memberDetails.getUsername());
       if(!member.isPresent()){
           throw new MyExceptionHandler(MEMBER_NOT_FOUND);
       }
        List<ChatroomDTO> chatroomByMember = getChatroomsByMember(member.get());

        for(int i=chatroomByMember.size()-1;i>=0;i--){
            if(chatroomByMember.get(i).isStatus() == false){
                chatroomByMember.remove(i);
            }
        }

        List<ChatroomDTO.chatroomList> chatroomList = new ArrayList<>();
        for(ChatroomDTO chatroomDTO : chatroomByMember){
            chatroomList.add(ChatroomDTO.toList(chatroomDTO));
        }

        return chatroomList;
    }

}
