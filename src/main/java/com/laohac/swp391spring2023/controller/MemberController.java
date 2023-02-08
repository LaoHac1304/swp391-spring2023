package com.laohac.swp391spring2023.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laohac.swp391spring2023.model.dto.MemberDTOReponse;
import com.laohac.swp391spring2023.model.dto.MemberDTORequest;
import com.laohac.swp391spring2023.model.entities.Member;
import com.laohac.swp391spring2023.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired 
    MemberService memberService;

    @GetMapping("/login")
    public String showLogin(Model model) {
        
        Member member = new Member();
        model.addAttribute("member", member);
        return "home/login"; 
    }

    @GetMapping("/addEmployee")
    public String showEmployeePage(Model model){
        Member member = new Member();
        model.addAttribute("employee", member);
        return "adminDashboard/Adashboard";
    }

    @PostMapping("/memberLogin")
    public String loginMember(@ModelAttribute("member") Member member){
        MemberDTOReponse memberLogin = memberService.authenticate(member);
        if (memberLogin != null)
            return "adminDashboard/Adashboard";
        return "home/login";
    }

    @PostMapping("/add")
    public String addMember(Model model) {
        Member member = new Member();
        model.addAttribute("member", member);
        return "add-dashboard";
    }

    @GetMapping("/adminDB")
    public String showAdminDB(){
        return "adminDashboard/Adashboard";
    }

    

    @PostMapping("/sign-up")
    public String signUp(MemberDTORequest memberDTORequest){

        /*MemberDTOReponse member = memberService.authenticate(memberDTORequest);*/
        return null;
    }

    
    
}
