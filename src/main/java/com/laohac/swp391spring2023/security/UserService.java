package com.laohac.swp391spring2023.security;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.laohac.swp391spring2023.model.dto.UserDTOResponse;

public interface UserService {

    public UserDTOResponse login(OAuth2User user);
    
}
