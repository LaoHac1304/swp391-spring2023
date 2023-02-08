package com.laohac.swp391spring2023.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.laohac.swp391spring2023.model.dto.MemberDTOReponse;
import com.laohac.swp391spring2023.model.dto.MemberDTORequest;

import com.laohac.swp391spring2023.model.entities.Member;
import com.laohac.swp391spring2023.repository.MemberRepository;
import com.laohac.swp391spring2023.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    
    private PasswordEncoder passwordEncoder;
    

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

    @Override
    public void addMember(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }

    
    
}
