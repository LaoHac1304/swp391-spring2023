package com.laohac.swp391spring2023.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laohac.swp391spring2023.model.entities.Car;
import com.laohac.swp391spring2023.model.entities.CarCompany;
import com.laohac.swp391spring2023.model.entities.Route;
import com.laohac.swp391spring2023.model.entities.Seat;
import com.laohac.swp391spring2023.model.entities.Trip;
import com.laohac.swp391spring2023.repository.CarRepository;
import com.laohac.swp391spring2023.repository.RouteRepository;
import com.laohac.swp391spring2023.repository.SeatRepository;
import com.laohac.swp391spring2023.repository.TripRepository;
import com.laohac.swp391spring2023.service.CarCompanyService;
import com.laohac.swp391spring2023.service.CarService;
import com.laohac.swp391spring2023.service.MemberService;
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
    MemberService memberService;

    @Autowired
    CarService carService;

    @Autowired
    CarRepository carRepository;

    @Autowired
    CarCompanyService carCompanyService;

    @Autowired
    SeatRepository seatRepository;

    @GetMapping("/viewall")
    public String viewAllTrip(@RequestParam("departure") String departure,
            @RequestParam("arrival") String arrival,
            Model model) {

        int id = memberService.getCurrentUser().getCarCompanyId();
        CarCompany carCompany = carCompanyService.getCarCompanyById(id);
        List<Car> cars = carService.getListCarByCarCompany(carCompany);
        Route route = routeRepository.findByState1AndState2(departure, arrival);
        List<Trip> lTrips = new ArrayList<>();

        // get today's date
        LocalDate today = LocalDate.now();

        for (Car car : cars) {
            List<Trip> listTrips = tripService.searchByRouteAndCar(route, car, true);
            for (Trip trip : listTrips) {
                // only include trips that occur today or in the future
                if (trip.getDate().isAfter(today) || trip.getDate().isEqual(today)) {
                    lTrips.add(trip);
                }
            }
        }

        model.addAttribute("listTrips", lTrips);
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

        int currentUserId = memberService.getCurrentUser().getCarCompanyId();
        model.addAttribute("currentUserId", currentUserId);

        return "CarCompanyDashboard/addTrip";
    }

    @GetMapping("/save")
    public String saveTrip(@ModelAttribute("trip") Trip trip, HttpSession session) {
        List<Trip> trips = tripRepository.findAll();
        
        for (Trip oldTrip : trips) {
            if (oldTrip.getCar().getId() == trip.getCar().getId()) {
                session.setAttribute("errorCarExist", "This car was choosen for another trip! Please choose the another.");
                return "redirect:/trip/add";
            }
        }
        for (int i = 0; i < 7; i++) {
            Trip newTrip = new Trip();
            newTrip.setRoute(trip.getRoute());
            newTrip.setStartTime(trip.getStartTime());
            newTrip.setEndTime(trip.getEndTime());
            newTrip.setCar(trip.getCar());
            newTrip.setPrice(trip.getPrice());
            newTrip.setDepartureDetail(trip.getDepartureDetail());
            newTrip.setArrivalDetail(trip.getArrivalDetail());
            newTrip.setDate(trip.getDate().plusDays(i));
            newTrip.setIsEnable(true);
            newTrip.setIsBiggestDay(i == 6);

            tripRepository.save(newTrip);

            Car car = carRepository.findById(trip.getCar().getId())
                    .orElseThrow(() -> new RuntimeException("Car not found"));

            car.initSeats(newTrip);

            carRepository.save(car);
        }
        return "redirect:/route/viewall";
    }

    @GetMapping("/updateListTrip/{id}")
    public String updateListTrip(@PathVariable(value = "id") int id) {
        Trip oldTrip = tripService.getTripById(id);
        if (oldTrip != null) {
            if (oldTrip.getIsBiggestDay() == false) {
                oldTrip = tripRepository.findByRouteAndCarAndIsBiggestDay(oldTrip.getRoute(), oldTrip.getCar(), true);
                oldTrip.setIsBiggestDay(false);
                tripRepository.save(oldTrip);
                LocalDate nextDate = oldTrip.getDate().plusDays(1);
                for (int i = 0; i < 7; i++) {
                    Trip newTrip = new Trip();
                    newTrip.setRoute(oldTrip.getRoute());
                    newTrip.setStartTime(oldTrip.getStartTime());
                    newTrip.setEndTime(oldTrip.getEndTime());
                    newTrip.setCar(oldTrip.getCar());
                    newTrip.setPrice(oldTrip.getPrice());
                    newTrip.setDepartureDetail(oldTrip.getDepartureDetail());
                    newTrip.setArrivalDetail(oldTrip.getArrivalDetail());
                    newTrip.setDate(nextDate.plusDays(i));
                    newTrip.setIsEnable(true);
                    newTrip.setIsBiggestDay(i == 6);
                    tripRepository.save(newTrip);

                    Car car = carRepository.findById(oldTrip.getCar().getId())
                            .orElseThrow(() -> new RuntimeException("Car not found"));

                    car.initSeats(newTrip);

                    carRepository.save(car);
                }
            } else {
                oldTrip.setIsBiggestDay(false);
                tripRepository.save(oldTrip);
                LocalDate nextDate = oldTrip.getDate().plusDays(1);
                for (int i = 0; i < 7; i++) {
                    Trip newTrip = new Trip();
                    newTrip.setRoute(oldTrip.getRoute());
                    newTrip.setStartTime(oldTrip.getStartTime());
                    newTrip.setEndTime(oldTrip.getEndTime());
                    newTrip.setCar(oldTrip.getCar());
                    newTrip.setPrice(oldTrip.getPrice());
                    newTrip.setDepartureDetail(oldTrip.getDepartureDetail());
                    newTrip.setArrivalDetail(oldTrip.getArrivalDetail());
                    newTrip.setDate(nextDate.plusDays(i));
                    newTrip.setIsEnable(true);
                    newTrip.setIsBiggestDay(i == 6);

                    tripRepository.save(newTrip);

                    Car car = carRepository.findById(oldTrip.getCar().getId())
                            .orElseThrow(() -> new RuntimeException("Car not found"));

                    car.initSeats(newTrip);

                    carRepository.save(car);
                }
            }
        }
        return "redirect:/route/viewall";
    }

    @GetMapping("/update/{id}")
    public String updateTrip(@PathVariable(value = "id") int id, Model model) {
        Trip trip = tripService.getTripById(id);
        model.addAttribute("trip", trip);

        List<Route> routes = routeRepository.findAll();
        model.addAttribute("routes", routes);

        List<Car> cars = carRepository.findAll();
        model.addAttribute("cars", cars);

        int currentUserId = memberService.getCurrentUser().getCarCompanyId();
        model.addAttribute("currentUserId", currentUserId);

        return "CarCompanyDashboard/updateTrip";
    }

    @GetMapping("/delete/{id}")
    public String deleteTrip(@PathVariable(value = "id") int id) {
        Trip trip = tripService.getTripById(id);
        trip.setIsEnable(false);
        tripRepository.save(trip);
        return "redirect:/route/viewall";

        // this.tripService.deleteTripById(id);
        // return "redirect:/route/viewall";
    }
}
