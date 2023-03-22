package com.laohac.swp391spring2023.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laohac.swp391spring2023.model.entities.Car;
import com.laohac.swp391spring2023.model.entities.CarCompany;
import com.laohac.swp391spring2023.repository.CarRepository;
import com.laohac.swp391spring2023.service.CarService;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepository carRepository;

    @Override
    public List<Car> getAllCar() {
        return this.carRepository.findAll();
    }

    @Override
    public Car getCarById(int id) {
        Optional<Car> optional = carRepository.findById(id);
        Car car = null;
        if(optional.isPresent()){
            car = optional.get();
        }else{
            throw new RuntimeException("Not found");
        }
        return car;
    }

    @Override
    public void deleteCarById(int id) {
        this.carRepository.deleteById(id);
    }

    @Override
    public List<Car> getListCarByCarCompany(CarCompany carCompany) {
        return this.carRepository.findByCarCompany(carCompany);
    }


    
}
