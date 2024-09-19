package com.example.meeTeam.evaluation.service;

import com.example.meeTeam.evaluation.Evaluation;
import com.example.meeTeam.evaluation.dto.EvaluationDTO;
import com.example.meeTeam.evaluation.dto.EvaluationRequestDTO;
import com.example.meeTeam.evaluation.dto.EvaluationResponseDTO;
import com.example.meeTeam.evaluation.repository.EvaluationRepository;
import com.example.meeTeam.global.handler.MyExceptionHandler;
import com.example.meeTeam.member.model.Member;
import com.example.meeTeam.member.dto.MemberDetails;
import com.example.meeTeam.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.meeTeam.global.exception.codes.ErrorCode.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class EvaluationServicelmpl implements EvaluationService{

    private final EvaluationRepository evaluationRepository;
    private final MemberRepository memberRepository;

    public void updateScore(Member member){
        List<Evaluation> evaluationList = evaluationRepository.findByMember(member);

        if(evaluationList == null || evaluationList.isEmpty()) throw new MyExceptionHandler(EVALUATION_NOT_FOUND);

        int numOfList = evaluationList.size();
        Double sumOfEvaluation = 0.0,evaluationScore;

        for(Evaluation evaluation : evaluationList){
            sumOfEvaluation+=evaluation.getEvaluationScore();
        }

        evaluationScore = sumOfEvaluation / numOfList;

        Optional<Member> memberByEmail = memberRepository.findMemberByMemberEmail(member.getMemberEmail());

        memberByEmail.ifPresent(t ->{
                t.updateMannerTemp(evaluationScore);
            this.memberRepository.save(t);
        });
    }

    public void doEvaluation(MemberDetails memberDetails, EvaluationRequestDTO.doingEvaluationDTO evaluationDTO){

        int numOfUsername = evaluationDTO.targetMembersEmail().size();
        int numOfScore = evaluationDTO.scoreList().size();

        //두개 숫자 맞는지 확인
        if(numOfScore != numOfUsername) throw new MyExceptionHandler(NOT_VALID_ERROR);

        Optional<Member> member = memberRepository.findMemberByMemberEmail(memberDetails.getUsername());
        if(!member.isPresent()) throw new MyExceptionHandler(MEMBER_NOT_FOUND);

        List<Member> targetMemberList = new ArrayList<>();
        for(int i=0;i<numOfUsername;i++){
            Optional<Member> tmpMember = memberRepository.findMemberByMemberEmail(evaluationDTO.targetMembersEmail().get(i));
            if(tmpMember.isPresent()) targetMemberList.add(tmpMember.get());
            else throw new MyExceptionHandler(MEMBER_NOT_FOUND);
        }

        List<Evaluation> evaluationList = new ArrayList<>();
        for(int i=0;i<numOfScore;i++){
            evaluationList.add(
                    new Evaluation(
                        evaluationDTO.scoreList().get(i),
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

    public void beforeDoingEvaluation(Member member, Member targetMember){
        Evaluation evaluation = new Evaluation(0,member,targetMember, false);
        evaluationRepository.save(evaluation);
    }

    public List<Evaluation> getEvalutaionByMember(Member member){
        List<Evaluation> evaluationByMember = evaluationRepository.findByMember(member);

        if(evaluationByMember == null || evaluationByMember.isEmpty()) throw new MyExceptionHandler(BAD_REQUEST);
        return evaluationByMember;
    }

    public List<EvaluationResponseDTO.beforeEvaluation> beforeDoingEvaluationListByMember(MemberDetails member){
        Optional<Member> tmpMember = memberRepository.findMemberByMemberName(member.getUsername());
        tmpMember.orElseThrow(() -> new MyExceptionHandler(BAD_REQUEST));
        Member nowMember = tmpMember.get();

        List<Evaluation> evaluationList = getEvalutaionByMember(nowMember);
        List<EvaluationResponseDTO.beforeEvaluation> responseEvaluation = new ArrayList<>();
        for(Evaluation evaluation : evaluationList){
            if(evaluation.isComplete()==true) responseEvaluation.add(new EvaluationResponseDTO.beforeEvaluation(evaluation.getMember(),evaluation.getTargetMember()));
        }

        return responseEvaluation;
    }


}
