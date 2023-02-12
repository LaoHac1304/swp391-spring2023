package com.laohac.swp391spring2023.service;

import com.laohac.swp391spring2023.model.dto.UserDTORequest;
import com.laohac.swp391spring2023.model.dto.UserDTOResponse;
import com.laohac.swp391spring2023.model.entities.User;

public interface UserService {

    public UserDTOResponse registerUser(User user);

    public UserDTOResponse login(UserDTORequest userDTORequest);

    public UserDTOResponse getUserInfo(String username);

    public UserDTOResponse authenticated(UserDTORequest userDTORequest);

    //public UserDTOResponse login(OAuth2User user);
    
}
