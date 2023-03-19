package com.laohac.swp391spring2023.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laohac.swp391spring2023.model.entities.Car;
import com.laohac.swp391spring2023.repository.CarRepository;
import com.laohac.swp391spring2023.service.CarService;

@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;

    @Autowired
    CarRepository carRepository;

    @GetMapping("/viewall")
    public String viewAllCar(Model model) {
        List<Car> listCars = carService.getAllCar();
        System.out.println(listCars);
        model.addAttribute("listCars", listCars);
        return "CarCompanyDashboard/CarManagement";
    }

    @GetMapping("/add")
    public String addTrip(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);

        return "CarCompanyDashboard/addCar";
    }

    @GetMapping("/save")
    public String saveTrip(@ModelAttribute("car") Car car) {
        car.initSeats();
        carRepository.save(car);

        return "redirect:/car/viewall";
    }

    @GetMapping("/update/{id}")
    public String updateTrip(@PathVariable(value = "id") int id, Model model) {
        Car car = carService.getCarById(id);
        model.addAttribute("car", car);

        return "CarCompanyDashboard/updateCar";
    }

    @GetMapping("/delete/{id}")
    public String deleteTrip(@PathVariable(value = "id") int id) {
        this.carService.deleteCarById(id);
        return "redirect:/car/viewall";
    }
}
