package com.laohac.swp391spring2023.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.laohac.swp391spring2023.model.dto.MemberDTORequest;
import com.laohac.swp391spring2023.model.entities.User;
import com.laohac.swp391spring2023.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired 
    MemberService memberService;

    @GetMapping("/adminDB")
    public String showAdminDB(HttpSession session){
       session.setAttribute("userSession", memberService.getCurrentUser());
       return "adminDashboard/Adashboard";
    }

    @GetMapping("/login")
    public String showAdminLogin(){
        return "home/login";
    }

    
    @GetMapping("/viewall")
    public String viewAllMember(Model model) {
        model.addAttribute("listMembers", memberService.getAllMember());
        return "adminDashboard/Adashboard";
    }

    @GetMapping("/add")
    public String addMember(Model model) {
        User member = new User();
        model.addAttribute("member", member);
    
        return "adminDashboard/addEmployee";
    }

    @GetMapping("/save")
    public String saveMember(@ModelAttribute("member") User member) {
        memberService.addMember(member);
        System.out.println(member);
        return "redirect:/member/viewall";
    }

    @GetMapping("/update/{id}")
    public String updateMember(@PathVariable(value = "id") int id, Model model) {
        User member = memberService.getMemberById(id);
        model.addAttribute("member", member);
        return "adminDashboard/updateEmployee";
    }

    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable(value = "id") int id) {
        this.memberService.deleteMemberById(id);
        return "redirect:/member/viewall";
    }

    @GetMapping("/logout")
      public String fetchSignoutSite(HttpServletRequest request, HttpServletResponse response) {        
        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();
  
        session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
  
        return "redirect:/member/login";
      }
    
    
}
