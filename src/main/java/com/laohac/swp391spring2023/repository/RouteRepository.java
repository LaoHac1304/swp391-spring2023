package com.laohac.swp391spring2023.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.laohac.swp391spring2023.model.entities.Route;

public interface RouteRepository extends JpaRepository<Route, Integer> {

    @Query("SELECT r FROM Route r WHERE r.departure = ?1 AND r.arrival = ?2")
    public Route findByState1AndState2(String departure, String arrival);

}
