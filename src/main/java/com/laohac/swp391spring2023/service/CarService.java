package com.laohac.swp391spring2023.service;

import java.util.List;

import com.laohac.swp391spring2023.model.entities.Car;

public interface CarService {

    public List<Car> getAllCar();

    public Car getCarById(int id);

    public void deleteCarById(int id);
    
}
