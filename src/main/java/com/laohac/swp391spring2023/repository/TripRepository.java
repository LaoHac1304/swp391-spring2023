package com.laohac.swp391spring2023.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laohac.swp391spring2023.model.entities.Route;
import com.laohac.swp391spring2023.model.entities.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

    public List<Trip> findByRoute(Route route);
    
}
