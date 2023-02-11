package com.laohac.swp391spring2023.service.impl;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.laohac.swp391spring2023.model.dto.UserDTORequest;
import com.laohac.swp391spring2023.model.dto.UserDTOResponse;
import com.laohac.swp391spring2023.model.entities.User;
import com.laohac.swp391spring2023.repository.UserRepository;
import com.laohac.swp391spring2023.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTOResponse registerUser(User user) {
        
        // validation user
        
        // successful validation
        userRepository.save(user);
        UserDTOResponse userDTOResponse = UserDTOResponse
                                .builder()
                                .fullName(user.getFullName())
                                .username(user.getUsername())
                                .build();
        return userDTOResponse;
    }

    @Override
    public UserDTOResponse login(UserDTORequest userDTORequest) {
        
        // authenticated
        String username = userDTORequest.getUsername();
        String password = userDTORequest.getPassword();

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            if (!user.getPassword().equals(password)) return null;
            UserDTOResponse userDTOResponse = UserDTOResponse
                                                .builder()
                                                .email(user.getEmail())
                                                .username(user.getUsername())
                                                .fullName(user.getFullName())
                                                .phoneNumber(user.getPhoneNumber())
                                                .sex(user.getSex())
                                                .build();
            return userDTOResponse;
        }

        return null;
    }

    @Override
    public UserDTOResponse getUserInfo(String username) {
        
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()){
            return null;
        }
        User user = userOptional.get();
        return UserDTOResponse
                        .builder()
                        .username(username)
                        .email(user.getEmail())
                        .fullName(user.getFullName())
                        .phoneNumber(user.getPhoneNumber())
                        .sex(user.getSex())               
                        .build();
    }

    /*@Override
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
        return userDTOResponse;*/
        
    }




