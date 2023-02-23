package com.laohac.swp391spring2023.repository;

import java.util.List;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;


import com.laohac.swp391spring2023.model.entities.Route;
import com.laohac.swp391spring2023.model.entities.Trip;


public interface TripRepository extends JpaRepository<Trip, Integer> {

    public List<Trip> findByRoute(Route route);
    public Trip findById(int id);
    public List<Trip> findByRouteAndDate(Route route, LocalDate date);
}
