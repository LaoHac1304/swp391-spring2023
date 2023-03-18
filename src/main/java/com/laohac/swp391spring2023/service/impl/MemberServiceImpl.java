package com.laohac.swp391spring2023.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.laohac.swp391spring2023.model.dto.ProfitDTOReponse;
import com.laohac.swp391spring2023.model.entities.OrderDetail;
import com.laohac.swp391spring2023.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.laohac.swp391spring2023.model.Provider;
import com.laohac.swp391spring2023.model.dto.MemberDTOReponse;
import com.laohac.swp391spring2023.model.dto.UserDTOResponse;
import com.laohac.swp391spring2023.model.entities.User;
import com.laohac.swp391spring2023.repository.MemberRepository;
import com.laohac.swp391spring2023.repository.UserRepository;
import com.laohac.swp391spring2023.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    
    @Override
    public MemberDTOReponse authenticate(User memberDTORequest) {
        
        Optional<User> memberOptional = memberRepository.findByUsername(memberDTORequest.getUsername());
        if (memberOptional.isPresent()){
            User member = memberOptional.get();
            if (!member.getPassword().equals(memberDTORequest.getPassword())) return null;
            return MemberDTOReponse.builder()
                                    .username(member.getUsername())
                                    .name(member.getFullName())
                                    .build();
        }
        return null;
    }

    public List<User> getAllMember() {  

        return memberRepository.findAllByRole("employee");
    }
    @Override
    public User getMemberById(int id) {
        Optional<User> optional = memberRepository.findById(id);
        User member = null;
        if(optional.isPresent()){
            member = optional.get();
        }else{
            throw new RuntimeException("Not found");
        }
        return member;
    }
    @Override
    public void deleteMemberById(int id) {
        this.memberRepository.deleteById(id);
        
    }
    @Override
    public void addMember(User member) {
        member.setRole("employee");
        member.setSex("Male");

        String rawPassword = member.getPassword();
        member.setPassword(passwordEncoder.encode(rawPassword));
        member.setProvider(Provider.LOCAL);
        
        this.memberRepository.save(member);       
    }

    @Override
    public UserDTOResponse getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            Optional<User> uOptional = memberRepository.findByUsername(username);
            Optional<User> uOptional2 = userRepository.findByEmail(username);
            User member = null;
            if (uOptional.isPresent())
                member = uOptional.get();
            else
                if (uOptional2.isPresent())
                {
                    member = uOptional2.get();
                }
            
            return UserDTOResponse
            .builder()
            .username(username)
            .fullName(member.getFullName())
            .email(member.getEmail())
            .sex(member.getSex())
            .phoneNumber(member.getPhoneNumber())
            .role(member.getRole())
            .build();    
        }
        return null;
    }

   


}
