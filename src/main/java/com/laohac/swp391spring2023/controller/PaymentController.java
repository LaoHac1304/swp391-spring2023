package com.laohac.swp391spring2023.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laohac.swp391spring2023.model.entities.Route;
import com.laohac.swp391spring2023.model.entities.Seat;
import com.laohac.swp391spring2023.model.entities.Trip;
import com.laohac.swp391spring2023.repository.RouteRepository;
import com.laohac.swp391spring2023.repository.SeatRepository;
import com.laohac.swp391spring2023.repository.TripRepository;
import com.laohac.swp391spring2023.service.BookingService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/booking")
@RequiredArgsConstructor
public class PaymentController {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/{id}")
    public String payment(@PathVariable(value = "id") int id, Model model){

        Trip trip = new Trip();
        
        trip = tripRepository.findById(id);
        model.addAttribute("tripInfoCurrent", trip);
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

    @GetMapping("/detail")
    public String showDetail(){
        return "home/ArrivalDepartureDetail";
    }

    @GetMapping("/checkout")
    public String showCheckoutForm(){
        return "home/checkoutForm";
    }



    @GetMapping("/showAllSeats")
    public String getAllSeats(Model model){
        Trip trip = tripRepository.findById(1);
        List<Seat> seatLists = seatRepository.findByTrip(trip);
        
        model.addAttribute("listSeats",seatLists);
        return "home/orderForm";
    }

    @PostMapping("/choose-seats")
    public String chooseSeats(@RequestParam(name = "selectedSeats", required = false) List<Integer> selectedSeats){

        

        for (Integer id : selectedSeats) {
            bookingService.chooseSeats(id);    
        }
       //return "redirect:/booking/showAllSeats";
       return "home/checkoutForm";
    }
}
