package com.laohac.swp391spring2023.service.impl;



import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.laohac.swp391spring2023.config.CustomOAuth2User;
import com.laohac.swp391spring2023.model.Provider;
import com.laohac.swp391spring2023.model.dto.UserDTORequest;
import com.laohac.swp391spring2023.model.dto.UserDTOResponse;
import com.laohac.swp391spring2023.model.dto.UserDTOUpdate;
import com.laohac.swp391spring2023.model.entities.Route;
import com.laohac.swp391spring2023.model.entities.Trip;
import com.laohac.swp391spring2023.model.entities.User;
import com.laohac.swp391spring2023.repository.TripRepository;
import com.laohac.swp391spring2023.repository.UserRepository;
import com.laohac.swp391spring2023.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder2;

    @Autowired
    private TripRepository tripRepository;

    @Override
    public UserDTOResponse registerUser(User user) {
        
        // validation user
        
        // successful validation
        user.setRole("customer");
        String rawPassword = user.getPassword();
        user.setPassword(passwordEncoder2.encode(rawPassword));
        user.setProvider(Provider.LOCAL);
        userRepository.save(user);
        UserDTOResponse userDTOResponse = UserDTOResponse
                                .builder()
                                .fullName(user.getFullName())
                                .username(user.getUsername())
                                .role("customer")
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

    @Override
    public UserDTOResponse authenticated(UserDTORequest userDTORequest) {
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
                                                .role(user.getRole())
                                                .build();
            return userDTOResponse;
        }

        return null;
    }


    @Override
    public UserDTOResponse update(UserDTOUpdate userUpdate, String username) {
        
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            user.setFullName(userUpdate.getFullName());
            user.setPhoneNumber(userUpdate.getPhoneNumber());
            user.setSex(userUpdate.getSex());
            userRepository.save(user);

            UserDTOResponse userDTOResponse = UserDTOResponse
                                                .builder()
                                                .email(user.getEmail())
                                                .username(user.getUsername())
                                                .fullName(user.getFullName())
                                                .phoneNumber(user.getPhoneNumber())
                                                .sex(user.getSex())
                                                .role(user.getRole())
                                                .build();
            return userDTOResponse;
        }

        return null;
    }

    @Override
    public UserDTOResponse getCurrentUser() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean checkEmailExisted(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) return true;
        return false;
    }

    @Override
    public void createUserByEmail(CustomOAuth2User oAuth2User) {
        
        String email = oAuth2User.getEmail();
        String fullName = oAuth2User.getFullName();

        User user = User.builder().email(email).fullName(fullName).role("customer").provider(Provider.GOOGLE).build();

        userRepository.save(user);
        
    }

    @Override
    public List<Trip> search(Route route) {
        
        List<Trip> trips = tripRepository.findByRoute(route);
        return trips;
    
    }

    @Override
    public List<Trip> searchByRouteAndDate(Route route, LocalDate date) {
        
        List<Trip> trips = tripRepository.findByRouteAndDate(route, date);
        return trips;
       
    }
    }




