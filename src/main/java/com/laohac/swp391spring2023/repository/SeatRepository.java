package com.laohac.swp391spring2023.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laohac.swp391spring2023.model.entities.Car;
import com.laohac.swp391spring2023.model.entities.Seat;
import com.laohac.swp391spring2023.model.entities.Trip;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

    public List<Seat> findByTrip(Trip tripID);

    public Seat findById(int seatID);

    public List<Seat> findByCarAndTrip(Car car, Trip trip);

}
