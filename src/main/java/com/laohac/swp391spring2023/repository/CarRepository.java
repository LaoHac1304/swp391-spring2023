package com.laohac.swp391spring2023.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.laohac.swp391spring2023.model.entities.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {

   
    
}
