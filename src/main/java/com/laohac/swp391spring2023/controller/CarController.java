package com.laohac.swp391spring2023.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laohac.swp391spring2023.model.entities.Car;
import com.laohac.swp391spring2023.model.entities.CarCompany;
import com.laohac.swp391spring2023.model.entities.Seat;
import com.laohac.swp391spring2023.repository.CarCompanyRepository;
import com.laohac.swp391spring2023.repository.CarRepository;
import com.laohac.swp391spring2023.repository.SeatRepository;
import com.laohac.swp391spring2023.service.CarCompanyService;
import com.laohac.swp391spring2023.service.CarService;
import com.laohac.swp391spring2023.service.MemberService;

@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;

    @Autowired
    CarRepository carRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    CarCompanyService carCompanyService;

    @Autowired
    CarCompanyRepository carCompanyRepository;

    @Autowired
    SeatRepository seatRepository;

    @GetMapping("/viewall")
    public String viewAllCar(Model model) {
        int id = memberService.getCurrentUser().getCarCompanyId();            
        CarCompany carCompany = carCompanyService.getCarCompanyById(id);
        List<Car> listCars = carService.getListCarByCarCompany(carCompany);
        System.out.println(listCars);
        model.addAttribute("listCars", listCars);
        return "CarCompanyDashboard/CarManagement";
    }

    @GetMapping("/add")
    public String addCar(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);

        int currentUserId = memberService.getCurrentUser().getCarCompanyId();
        model.addAttribute("currentUserId", currentUserId);
        
        List<CarCompany> carCompanys = carCompanyRepository.findAll();
        model.addAttribute("carCompanys", carCompanys);

        return "CarCompanyDashboard/addCar";
    }

    @GetMapping("/save")
    public String saveCar(@ModelAttribute("car") Car car, Model model) {
        if (carRepository.findByPlateNumber(car.getPlateNumber()) != null) {
            model.addAttribute("error", "Plate number already exists");
            int currentUserId = memberService.getCurrentUser().getCarCompanyId();
            model.addAttribute("currentUserId", currentUserId);
            List<CarCompany> carCompanys = carCompanyRepository.findAll();
            model.addAttribute("carCompanys", carCompanys);
            return "CarCompanyDashboard/addCar";
        }
        carRepository.save(car);

        List<Seat> seats = new ArrayList<>();
    for (int i = 1; i <= car.getCapacity(); i++) {
        Seat seat = new Seat();
        seat.setSeatNumber(i);
        seat.setCar(car);
        seats.add(seat);
    }
    seatRepository.saveAll(seats);
        return "redirect:/car/viewall";
    }

    @GetMapping("/update/{id}")
    public String updateTrip(@PathVariable(value = "id") int id, Model model) {
        Car car = carService.getCarById(id);
        model.addAttribute("car", car);

        int currentUserId = memberService.getCurrentUser().getCarCompanyId();
        model.addAttribute("currentUserId", currentUserId);

        List<CarCompany> carCompanys = carCompanyRepository.findAll();
        model.addAttribute("carCompanys", carCompanys);

        return "CarCompanyDashboard/updateCar";
    }

    @GetMapping("/delete/{id}")
    public String deleteTrip(@PathVariable(value = "id") int id) {
        this.carService.deleteCarById(id);
        return "redirect:/car/viewall";
    }
}
