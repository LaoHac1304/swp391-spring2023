package com.laohac.swp391spring2023.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/homepage")
public class HomeController {
    
    @GetMapping("/")
    public String showHomePage(){
        return "home/index";
    }
    
    
}
