package com.laohac.swp391spring2023.service.impl;

import java.util.List;

import com.laohac.swp391spring2023.model.dto.TripDTO;
import com.laohac.swp391spring2023.model.entities.Car;
import com.laohac.swp391spring2023.model.entities.Route;
import com.laohac.swp391spring2023.model.entities.Trip;
import com.laohac.swp391spring2023.service.TripService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laohac.swp391spring2023.repository.TripRepository;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    TripRepository tripRepository;

    @Override
    public void addTrip(TripDTO tripDTO) {
        Trip trip = new Trip();
        Route route = new Route();
        route.setDeparture(tripDTO.getRoute().getDeparture());
        route.setArrival(tripDTO.getRoute().getArrival());
    
        Car car = new Car();
        car.setId(tripDTO.getCar().getId());
    
        trip.setRoute(route);
        trip.setStartTime(tripDTO.getStartTime());
        trip.setEndTime(tripDTO.getEndTime());
        trip.setCar(car);
        trip.setPrice(tripDTO.getPrice());
        trip.setDepartureDetail(tripDTO.getDepartureDetail());
        trip.setArrivalDetail(tripDTO.getArrivalDetail());
        trip.setDate(tripDTO.getDate());
    
        tripRepository.save(trip);
    }

    @Override
    @Transactional
    public List<Trip> getAllTrip(){
        return tripRepository.findAll();
    }
}