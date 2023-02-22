package com.laohac.swp391spring2023.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.laohac.swp391spring2023.model.entities.User;
import com.laohac.swp391spring2023.repository.MemberRepository;


public class AdminUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<User> uOptional = memberRepository.findByUsername(username);
        if (!uOptional.isPresent()){
            throw new UsernameNotFoundException("No user found for the given username");
        }
        User user = uOptional.get();
        
        return new AdminUserDetail(user);
    }
    
}
