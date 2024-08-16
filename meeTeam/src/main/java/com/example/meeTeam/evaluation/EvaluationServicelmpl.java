package com.example.meeTeam.evaluation;

import com.example.meeTeam.global.handler.MyExceptionHandler;
import com.example.meeTeam.member.Member;
import com.example.meeTeam.member.dto.MemberDetails;
import com.example.meeTeam.member.repository.MemberRepository;
import com.example.meeTeam.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.meeTeam.global.exception.codes.ErrorCode.*;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class EvaluationServicelmpl implements EvaluationService{

    private final EvaluationRepository evaluationRepository;
    private final MemberRepository memberRepository;

    public void updateScore(Member member){
        List<Evaluation> evaluationList = Optional.ofNullable(evaluationRepository.findByMember(member))
                .orElseGet(() -> new ArrayList<>());

        if(evaluationList.isEmpty()){
            //업데이트 할게 없음
            throw new MyExceptionHandler(EVALUATION_NOT_FOUND);
        }

        int numOfList = evaluationList.size();
        Double sumOfEvaluation = 0.0,evaluationScore;

        for(Evaluation evaluation : evaluationList){
            sumOfEvaluation+=evaluation.getEvaluationScore();
        }

        evaluationScore = sumOfEvaluation / numOfList;

        Optional<Member> memberByEmail = memberRepository.findMemberByEmail(member.getEmail());
        memberByEmail.ifPresent(t ->{
                t.updateMannerTemp(evaluationScore);
            this.memberRepository.save(t);
        });
        member.getMemberMannerTemp(); //여기에 변경하고 싶은데
    }

    public void doEvaluation(MemberDetails memberDetails,EvaluationDTO.doingEvaluationDTO evaluationDTO){

        int numOfUsername = evaluationDTO.getTargetMembersEmail().size();
        int numOfScore = evaluationDTO.getScoreList().size();

        //두개 숫자 맞는지 확인
        if(numOfScore != numOfUsername) throw new MyExceptionHandler(NOT_VALID_ERROR);

        Optional<Member> member = memberRepository.findMemberByEmail(memberDetails.getUsername());
        if(!member.isPresent()) throw new MyExceptionHandler(MEMBER_NOT_FOUND);

        List<Member> targetMemberList = new ArrayList<>();
        for(int i=0;i<numOfUsername;i++){
            Optional<Member> tmpMember = memberRepository.findMemberByEmail(evaluationDTO.getTargetMembersEmail().get(i));
            if(tmpMember.isPresent()) targetMemberList.add(tmpMember.get());
            else throw new MyExceptionHandler(MEMBER_NOT_FOUND);
        }

        List<Evaluation> evaluationList = new ArrayList<>();
        for(int i=0;i<numOfScore;i++){
            evaluationList.add(
                    new Evaluation(
                        evaluationDTO.getScoreList().get(i),
                        member.get(),
                        targetMemberList.get(i),
                        true
                    )
            );
        }

        for(Evaluation evaluation : evaluationList) evaluationRepository.save(evaluation);
    }

    public void deleteEvaluation(EvaluationDTO evaluationDTO){
        Evaluation evaluation = new Evaluation(evaluationDTO.getEvaluationScore(),evaluationDTO.getMember(),evaluationDTO.getTargetMember(),evaluationDTO.isComplete());
        evaluationRepository.delete(evaluation);
    }

}
