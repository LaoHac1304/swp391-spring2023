package com.laohac.swp391spring2023.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.laohac.swp391spring2023.model.entities.Member;
import com.laohac.swp391spring2023.repository.MemberRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<Member> memberOptional = memberRepository.findByUsername(username);

        if (!memberOptional.isPresent()){
            System.out.println("Username not found");
            throw new UsernameNotFoundException("User was not found in the database");
        }

        Member member = memberOptional.get();

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        
                // ROLE_USER, ROLE_ADMIN,..
                String role = member.getRole();
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            
        
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(member.getUsername(),
        member.getPassword(), (Collection<? extends GrantedAuthority>) authority);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, (Collection<? extends GrantedAuthority>) authority);

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        return userDetails;
    }
    
}
