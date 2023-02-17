package com.laohac.swp391spring2023.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.laohac.swp391spring2023.model.entities.User;
import com.laohac.swp391spring2023.repository.UserRepository;

public class CustomerUserDetailsService implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepository;
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> customerOptional = userRepository.findByUsername(username);
        if (!customerOptional.isPresent()){
            throw new UsernameNotFoundException("No user found for the given username");
        }
        User customer = customerOptional.get();
         
        return new CustomerUserDetails(customer);
    }
 
}
