package com.laohac.swp391spring2023.service;

import java.util.List;

import com.laohac.swp391spring2023.model.dto.MemberDTOReponse;
import com.laohac.swp391spring2023.model.entities.Member;

public interface MemberService {

    public MemberDTOReponse authenticate(Member memberDTORequest);
    public List<Member> getAllMember();
    public void addMember(Member member);
    public Member getMemberById(int id);
    public void deleteMemberById(int id);
}
