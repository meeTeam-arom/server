package com.example.meeTeam.chatroom;

import com.example.meeTeam.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ChatroomServicelmpl implements ChatroomService{

    private final ChatroomRepository chatroomRepository;
    private final MemberChatroomRepository memberChatroomRepository;
    private final ProjectRoleRepository projectRoleRepository;

   public  void test(ChatroomDTO.enterChatroom enterChatroom){
        System.out.println(enterChatroom.getCode());
        System.out.println(enterChatroom.isWantLeader());
    }

    public String calHalfwayPoint(ChatroomDTO chatroom){
       List<Member> members = getMemberByChatroomID(chatroom);
        int numberOfMembers = members.size();

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


    public String enterChatroom(ChatroomDTO.enterChatroom enterChatroom){

        //코드 맞는지 확인
        ChatroomDTO.chatroomCode code = new ChatroomDTO.chatroomCode();
        code.setCode(enterChatroom.getCode());
        ChatroomDTO chatroomDTO = getChatroomByCode(code);

        if(chatroomDTO == null){
            //검색한 코드가 없을경우 예외 처리
            System.out.println("enter the code correctly");
            return "enter the code correctly";
        }

        System.out.println("Chatroom 있는거 확인 / enter");

        Chatroom chatroom = Chatroom.toEntity(chatroomDTO);
        Member member = null;

        //인원수 꽉 찬지 확인
        List<Member> members = getMemberByChatroomID(chatroomDTO);
        int numberOfTotalMember = chatroomDTO.getTotalMember();
        int numberOfMember = members.size();

        System.out.println(numberOfMember+" "+numberOfTotalMember);

        if(numberOfMember >= numberOfTotalMember){
            //인원수가 초과됨 예외 처리
        }else if(numberOfMember < numberOfTotalMember){

            MemberChatroom memberChatroom = new MemberChatroom(
                    chatroom,
                    member,
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

            ProjectRole tmpRole = Optional.ofNullable(projectRoleRepository.findByMemberChatroom(memberChatroom))
                    .orElseGet(() -> null);

            if(tmpRole != null){
                projectRoleList.add(tmpRole);
                if(tmpRole.isWantLeader() == true){
                    numOfWantLeader++;
                    wantLeaderList.add(tmpRole);
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
        //처음 저장할 때 리더 원하는 사람은 leader로 하자
        //일단 멤버들 전원 값 가져옴.
        // 찬성인 사람들 중에 기여도를 등수 토대로 1등부터 선택권 줌
        // 만약에 다 안할 경우 랜덤값으로 한명만 넘김

        return "success";
    }

    public void saveChatrommInfo(Chatroom chatroom, MemberChatroom memberChatroom, ProjectRole role){

        chatroomRepository.save(chatroom);
        chatroom.addMemberChatroom(memberChatroom);

        memberChatroomRepository.save(memberChatroom);
        memberChatroom.addProjectRole(role);

        projectRoleRepository.save(role);
    }

    public ChatroomDTO createChatroom(ChatroomDTO.chatroomCreate data){
        Member member = null;
        System.out.println("enter createChatroom");

        ChatroomDTO.chatroomCode chatroomCode = createChatroomCode();

        System.out.println("chatroomCode : "+chatroomCode.getCode());

        //방만들기 누른 사람 member값을 가져와야함`
        //방만든 멤버 memberChatroom 에 추가

        Chatroom chatroom = new Chatroom(null,
                data.getChatroomName(),
                data.getTotoalMember(),
                chatroomCode.getCode(),
                new ArrayList<>(),
                new ArrayList<>()
        );


        MemberChatroom memberChatroom = new MemberChatroom(
                chatroom,
                member,
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
            //예외처리 필요
        }

        List<ChatroomDTO> chatrooms = new ArrayList<>();

        for(MemberChatroom memberChatroom : memberChatroomsByMember){
            chatrooms.add(ChatroomDTO.toDTO(memberChatroom.getChatroom()));
        }

        return chatrooms;
    }


    public List<Member> getMemberByChatroomID(ChatroomDTO chatroomDTO) {

        List<MemberChatroom> memberChatroomsByChatroom = Optional.ofNullable(memberChatroomRepository.findByChatroom(Chatroom.toEntity(chatroomDTO)))
                .orElseGet(() -> new ArrayList<>());

        List<MemberChatroom> test = new ArrayList<>();


        if(memberChatroomsByChatroom.isEmpty()){
            //데이터 가져오는거 예외 처리 필요
        }

        List<Member> members = new ArrayList<>();

        for (MemberChatroom memberChatroom : memberChatroomsByChatroom) {
            members.add(memberChatroom.getMember());
        }

        return members;
    }
}
