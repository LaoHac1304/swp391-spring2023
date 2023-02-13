package com.laohac.swp391spring2023.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laohac.swp391spring2023.model.dto.MemberDTOReponse;
import com.laohac.swp391spring2023.model.entities.User;
import com.laohac.swp391spring2023.repository.MemberRepository;
import com.laohac.swp391spring2023.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    
   
    

    @Override
    public MemberDTOReponse authenticate(User memberDTORequest) {
        
        Optional<User> memberOptional = memberRepository.findByUsername(memberDTORequest.getUsername());
        if (memberOptional.isPresent()){
            User member = memberOptional.get();
            if (!member.getPassword().equals(memberDTORequest.getPassword())) return null;
            return MemberDTOReponse.builder()
                                    .username(member.getUsername())
                                    .name(member.getFullName())
                                    .build();
        }
        return null;
    }

    public List<User> getAllMember() {  

        return memberRepository.findAllByRole("employee");
    }
    @Override
    public User getMemberById(int id) {
        Optional<User> optional = memberRepository.findById(id);
        User member = null;
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
    public void addMember(User member) {
        member.setRole("employee");
        member.setSex("Male");
        this.memberRepository.save(member);       
    }

    
    
}
