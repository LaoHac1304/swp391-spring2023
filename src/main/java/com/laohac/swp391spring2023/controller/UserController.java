package com.laohac.swp391spring2023.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;


import com.laohac.swp391spring2023.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    
    /*@GetMapping("/login-google")
    public String login(Model model, @AuthenticationPrincipal OAuth2User user){

        UserDTOResponse userLogin = userService.login(user);
        model.addAttribute("user", userLogin);

        return "index";
    }*/

    
}
