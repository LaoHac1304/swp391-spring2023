package com.laohac.swp391spring2023.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laohac.swp391spring2023.model.dto.MemberDTOReponse;
import com.laohac.swp391spring2023.model.entities.Member;
import com.laohac.swp391spring2023.repository.MemberRepository;
import com.laohac.swp391spring2023.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    
   
    

    @Override
    public MemberDTOReponse authenticate(Member memberDTORequest) {
        
        Optional<Member> memberOptional = memberRepository.findByUsername(memberDTORequest.getUsername());
        if (memberOptional.isPresent()){
            Member member = memberOptional.get();
            if (!member.getPassword().equals(memberDTORequest.getPassword())) return null;
            return MemberDTOReponse.builder()
                                    .username(member.getUsername())
                                    .name(member.getName())
                                    .build();
        }
        return null;
    }

    public List<Member> getAllMember() {      
        return memberRepository.findAll();
    }
    @Override
    public Member getMemberById(int id) {
        Optional<Member> optional = memberRepository.findById(id);
        Member member = null;
        if(optional.isPresent()){
            member = optional.get();
        }else{
            throw new RuntimeException("Not found");
        }
        return member;
    }
    @Override
    public void deleteMemberById(int id) {
        this.memberRepository.deleteById(id);
        
    }
    @Override
    public void addMember(Member member) {
        member.setRole("employee");
        this.memberRepository.save(member);       
    }

    
    
}
