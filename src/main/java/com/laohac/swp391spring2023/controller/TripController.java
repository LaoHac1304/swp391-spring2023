package com.laohac.swp391spring2023.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laohac.swp391spring2023.model.entities.Car;
import com.laohac.swp391spring2023.model.entities.Route;
import com.laohac.swp391spring2023.model.entities.Trip;
import com.laohac.swp391spring2023.repository.CarRepository;
import com.laohac.swp391spring2023.repository.RouteRepository;
import com.laohac.swp391spring2023.repository.TripRepository;
import com.laohac.swp391spring2023.service.TripService;

@Controller
@RequestMapping("/trip")
public class TripController {
    @Autowired
    TripService tripService;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    CarRepository carRepository;

    @GetMapping("/viewall")
    public String viewAllTrip(@RequestParam("departure") String departure,
    @RequestParam("arrival") String arrival,
    Model model) {
        Route route = routeRepository.findByState1AndState2(departure, arrival);
        List<Trip> listTrips = tripService.searchByRoute(route);
        System.out.println(listTrips);
        model.addAttribute("listTrips", listTrips);
        return "CarCompanyDashboard/TripManagement";
    }

    @GetMapping("/add")
    public String addTrip(Model model) {
        Trip trip = new Trip();
        model.addAttribute("trip", trip);

        List<Route> routes = routeRepository.findAll();
        model.addAttribute("routes", routes);

        List<Car> cars = carRepository.findAll();
        model.addAttribute("cars", cars);

        return "CarCompanyDashboard/addTrip";
    }

    @GetMapping("/save")
    public String saveTrip(@ModelAttribute("trip") Trip trip) {
        tripRepository.save(trip);
        return "redirect:/trip/viewall";
    }

    @GetMapping("/update/{id}")
    public String updateTrip(@PathVariable(value = "id") int id, Model model) {
        Trip trip = tripService.getTripById(id);
        model.addAttribute("trip", trip);

        List<Route> routes = routeRepository.findAll();
        model.addAttribute("routes", routes);

        List<Car> cars = carRepository.findAll();
        model.addAttribute("cars", cars);

        return "CarCompanyDashboard/updateTrip";
    }

    @GetMapping("/delete/{id}")
    public String deleteTrip(@PathVariable(value = "id") int id) {
        this.tripService.deleteTripById(id);
        return "redirect:/trip/viewall";
    }
}
