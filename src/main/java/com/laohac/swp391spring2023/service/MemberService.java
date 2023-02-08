package com.laohac.swp391spring2023.service;

import com.laohac.swp391spring2023.model.dto.MemberDTOReponse;
import com.laohac.swp391spring2023.model.dto.MemberDTORequest;
import com.laohac.swp391spring2023.model.entities.Member;

public interface MemberService {

    public MemberDTOReponse authenticate(Member memberDTORequest);
    public void addMember(Member member);
    
}
