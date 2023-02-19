package com.laohac.swp391spring2023.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/booking")
@RequiredArgsConstructor
public class PaymentController {
    @GetMapping("")
    public String payment(){
        return "home/orderForm";
    }
}
