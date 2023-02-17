package com.laohac.swp391spring2023.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.laohac.swp391spring2023.model.entities.User;
import com.laohac.swp391spring2023.service.MemberService;

@Controller
@RequestMapping("/homepage")
public class HomeController {

    @Autowired
    private MemberService memberService;

    
    @GetMapping("")
    public String showHomePage(){
        return "home/index";
    }

    @GetMapping("/adminDashBord")
    public String showAdminDashBoard(Model model){
        model.addAttribute("listMembers", memberService.getAllMember());
        return "adminDashboard/Adashboard";
    }

    @GetMapping("/customerDashBord")
    public String showCustomerDashBoard(){
        return "home/index";
    }

    @GetMapping("/login")
    public String showLogin(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "redirect:/users/login";
    }

    @GetMapping("/logout")
      public String fetchSignoutSite(HttpServletRequest request, HttpServletResponse response) {        
        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();
  
        session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
  
        return "/homepage";
        //return "homepage/login";
      }

    // @PostMapping("/sign-in")
    // public String login(HttpSession session ,Model model, @ModelAttribute("user") UserDTORequest userDTORequest){

    //     UserDTOResponse userDTOResponse = userService.authenticated(userDTORequest);
    //     if (userDTOResponse == null) return "home/login";
    //     session.setAttribute("userSession", userDTOResponse);
    //     if (userDTOResponse.getRole().equals("admin")){
    //         //return "adminDashboard/Adashboard";
    //         return "redirect:/member/viewall";
    //     }
    //     return "home/index";
    // }
    
    
}
