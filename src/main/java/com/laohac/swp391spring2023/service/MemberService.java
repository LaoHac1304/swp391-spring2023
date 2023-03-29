package com.laohac.swp391spring2023.service;

import java.util.List;

import com.laohac.swp391spring2023.model.dto.MemberDTOReponse;
import com.laohac.swp391spring2023.model.dto.UserDTOResponse;
import com.laohac.swp391spring2023.model.entities.User;

public interface MemberService {

    public MemberDTOReponse authenticate(User memberDTORequest);

    public List<MemberDTOReponse> getAllMember();

    public void addMember(User member);

    public User getMemberById(int id);

    public void deleteMemberById(int id);

    public UserDTOResponse getCurrentUser();

    public void updateMember(User member);

    
}
