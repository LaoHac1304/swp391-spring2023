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
    Optional<Trip> findTripById(int id);
    public List<Trip> findByRouteAndDateAndIsEnable(Route route, LocalDate date, Boolean isEnable);
    public List<Trip> findByRouteAndDateOrderByPriceDesc(Route route, LocalDate date);
    public List<Trip> findByRouteAndDateOrderByPriceAsc(Route route, LocalDate date);
    public List<Trip> findByRouteAndDateOrderByStartTimeDesc(Route route, LocalDate date);
    public List<Trip> findByRouteAndDateOrderByStartTimeAsc(Route route, LocalDate date);

    public List<Trip> findByRouteAndCarAndIsEnableOrderByDateAsc(Route route, Car car, Boolean isEnable);
    
    public List<Trip> findByCarAndIsBiggestDay(Car car, Boolean isBiggestDay);

    public Trip findTripByCarAndIsBiggestDay(Car car, Boolean isBiggestDay);

    public Trip findByRouteAndCarAndIsBiggestDay(Route route, Car car, Boolean isBiggestDay);
    
    public Trip findByIsBiggestDay(Boolean isBiggestDay);
}
