package com.laohac.swp391spring2023.repository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laohac.swp391spring2023.model.entities.Car;
import com.laohac.swp391spring2023.model.entities.Route;
import com.laohac.swp391spring2023.model.entities.Trip;


public interface TripRepository extends JpaRepository<Trip, Integer> {

    public List<Trip> findByRoute(Route route);
    public Trip findById(int id);
    public List<Trip> findByRouteAndDate(Route route, LocalDate date);
    public List<Trip> findByRouteAndDateOrderByPriceDesc(Route route, LocalDate date);
    public List<Trip> findByRouteAndDateOrderByPriceAsc(Route route, LocalDate date);
    public List<Trip> findByRouteAndDateOrderByStartTimeDesc(Route route, LocalDate date);
    public List<Trip> findByRouteAndDateOrderByStartTimeAsc(Route route, LocalDate date);

    public List<Trip> findByRouteAndCar(Route route, Car car);
    
}
