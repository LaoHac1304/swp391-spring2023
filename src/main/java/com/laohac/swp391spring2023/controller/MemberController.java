package com.laohac.swp391spring2023.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laohac.swp391spring2023.model.dto.MemberDTOReponse;
import com.laohac.swp391spring2023.model.dto.MemberDTORequest;
import com.laohac.swp391spring2023.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired 
    MemberService memberService;

    @GetMapping("/login")
    public String showLogin() {

        return "login/login";
    }

    @PostMapping("/sign-up")
    public String signUp(MemberDTORequest memberDTORequest){

        MemberDTOReponse member = memberService.authenticate(memberDTORequest);
        return null;
    }
    
}
