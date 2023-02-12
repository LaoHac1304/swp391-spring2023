package com.laohac.swp391spring2023.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.laohac.swp391spring2023.model.dto.UserDTORequest;
import com.laohac.swp391spring2023.model.dto.UserDTOResponse;

import com.laohac.swp391spring2023.model.entities.User;
import com.laohac.swp391spring2023.service.UserService;

@Controller
@RequestMapping("/homepage")
public class HomeController {

    @Autowired
    private UserService userService;
    
    @GetMapping("")
    public String showHomePage(){
        return "home/index";
    }

    @GetMapping("/adminDashBord")
    public String showAdminDashBoard(){
        return "adminDashboard/Adashboard";
    }

    @GetMapping("/login")
    public String showLogin(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "home/login";
    }

    @PostMapping("/sign-in")
    public String login(HttpSession session ,Model model, @ModelAttribute("user") UserDTORequest userDTORequest){

        UserDTOResponse userDTOResponse = userService.authenticated(userDTORequest);
        //model.addAttribute("user", userDTOResponse);
        session.setAttribute("userSession", userDTOResponse);
        if (userDTOResponse.getRole().equals("admin")){
            return "adminDashboard/Adashboard";
        }
        return "home/index";
    }
    
    
}
