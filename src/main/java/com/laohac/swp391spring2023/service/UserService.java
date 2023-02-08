package com.laohac.swp391spring2023.service;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.laohac.swp391spring2023.model.dto.UserDTOResponse;
import com.laohac.swp391spring2023.model.entities.Member;

public interface UserService {

    public UserDTOResponse login(OAuth2User user);
    
}
