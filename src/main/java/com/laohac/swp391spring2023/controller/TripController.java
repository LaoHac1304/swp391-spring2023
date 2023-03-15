package com.laohac.swp391spring2023.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laohac.swp391spring2023.model.entities.Trip;
import com.laohac.swp391spring2023.repository.CarRepository;
import com.laohac.swp391spring2023.model.dto.TripDTO;
import com.laohac.swp391spring2023.model.dto.RouteDTO;
import com.laohac.swp391spring2023.service.RouteService;
import com.laohac.swp391spring2023.service.TripService;

@Controller
@RequestMapping("/trip")
public class TripController {
    @Autowired
    TripService tripService;

    @Autowired
    RouteService routeService;

    @Autowired
    CarRepository carRepository;

    @GetMapping("/viewall")
    public String viewAllTrip(Model model) {
        List<Trip> listTrips = tripService.getAllTrip();
        System.out.println(listTrips);
        model.addAttribute("listTrips", listTrips);
        return "CarCompanyDashboard/CcDashboard";
    }

    @GetMapping("/add")
    public String addTrip(Model model) {      
        TripDTO tripDTO = new TripDTO();
        List<RouteDTO> routes = routeService.getAllRoute();
        model.addAttribute("routes", routes);
        model.addAttribute("trip", tripDTO);
        return "CarCompanyDashboard/addTrip";
    }

    @GetMapping("/save")
    public String saveTrip(@ModelAttribute("trip") TripDTO tripDTO) {
        tripService.addTrip(tripDTO);
        System.out.println(tripDTO);
        return "redirect:/trip/viewall";
    }
}
