package com.laohac.swp391spring2023.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laohac.swp391spring2023.model.entities.Route;
import com.laohac.swp391spring2023.repository.RouteRepository;
import com.laohac.swp391spring2023.service.MemberService;
import com.laohac.swp391spring2023.service.RouteService;

@Controller
@RequestMapping("/route")
public class RouteController {
    @Autowired
    RouteRepository routeRepository;
    @Autowired
    RouteService routeService;
    @Autowired
    MemberService memberService;

    @GetMapping("/viewall")
    public String viewAllRoute(Model model) {
        List<Route> listRoutes = routeService.getAllRoute();
        model.addAttribute("listRoutes", listRoutes);
        return "CarCompanyDashboard/RouteManagement";
    }
    @GetMapping("/add")
    public String addRoute(Model model) {
        Route route = new Route();
        model.addAttribute("route", route);
        return "CarCompanyDashboard/addRoute";
    }
    @GetMapping("/save")
    public String saveRoute(@ModelAttribute("route") Route route) {
        routeRepository.save(route);
        return "redirect:/route/viewall";
    }

    @GetMapping("/update/{id}")
    public String updateRoute(@PathVariable(value = "id") int id, Model model) {
        Route route = routeService.getRouteById(id);
        model.addAttribute("route", route);

        return "CarCompanyDashboard/updateRoute";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoute(@PathVariable(value = "id") int id) {
        this.routeService.deleteRouteById(id);
        return "redirect:/route/viewall";
    }
}
