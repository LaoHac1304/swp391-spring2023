package com.laohac.swp391spring2023.service;

import com.laohac.swp391spring2023.model.dto.MemberDTOReponse;
import com.laohac.swp391spring2023.model.dto.MemberDTORequest;

public interface MemberService {

    public MemberDTOReponse authenticate(MemberDTORequest memberDTORequest);
    
}
