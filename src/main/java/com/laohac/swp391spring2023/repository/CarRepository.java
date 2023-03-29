package com.laohac.swp391spring2023.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laohac.swp391spring2023.model.entities.Car;
import com.laohac.swp391spring2023.model.entities.CarCompany;

public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByCarCompany(CarCompany carCompany);
    public Car findByPlateNumber(String plateNumber);
}
