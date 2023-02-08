package com.laohac.swp391spring2023.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.laohac.swp391spring2023.model.dto.UserDTOResponse;
import com.laohac.swp391spring2023.model.entities.User;
import com.laohac.swp391spring2023.repository.UserRepository;
import com.laohac.swp391spring2023.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTOResponse login(OAuth2User user) {
        
        UserDTOResponse userDTOResponse = new UserDTOResponse();
        String username = user.getAttribute("given_name");
        String email = user.getAttribute("email");

        Optional<User> userOptional = userRepository.findByEmail(email);

        User newUser = new User();

        if (!userOptional.isPresent()){
            newUser = User.builder().fullName(username).email(email).build();
            userRepository.save(newUser);
        }

        userDTOResponse = UserDTOResponse
                                .builder()
                                .username(username)
                                .email(email)
                                .build();
        System.out.println(newUser);
        return userDTOResponse;
        
    }




}
