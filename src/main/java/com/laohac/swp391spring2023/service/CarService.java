package com.laohac.swp391spring2023.service;

import java.util.List;

import com.laohac.swp391spring2023.model.entities.Car;
import com.laohac.swp391spring2023.model.entities.CarCompany;

public interface CarService {

    public List<Car> getAllCar();

    public Car getCarById(int id);

    public List<Car> getListCarByCarCompany(CarCompany carCompany);

    public void deleteCarById(int id);
    
}
