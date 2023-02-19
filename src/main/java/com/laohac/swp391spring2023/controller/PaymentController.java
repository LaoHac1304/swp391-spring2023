package com.laohac.swp391spring2023.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laohac.swp391spring2023.model.entities.Route;
import com.laohac.swp391spring2023.repository.RouteRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/booking")
@RequiredArgsConstructor
public class PaymentController {

    @Autowired
    private RouteRepository routeRepository;

    @GetMapping("")
    public String payment(){
        return "home/orderForm";
    }

    @GetMapping("/list-place")
    public String listPlace(Model model) {

        List<Route> listRoute = routeRepository.findAll();
        model.addAttribute("listStates", listRoute);
        for (Route route : listRoute) {
            System.out.println(route.getArrival());
        }
        return "redirect:/users/search-trip";
    }
}
