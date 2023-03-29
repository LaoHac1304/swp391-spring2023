package com.laohac.swp391spring2023.service.impl;

import java.util.List;
import java.util.Optional;

import com.laohac.swp391spring2023.model.dto.TripDTO;
import com.laohac.swp391spring2023.model.entities.Car;
import com.laohac.swp391spring2023.model.entities.CarCompany;
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
        route.setId(tripDTO.getRouteId());
        trip.setRoute(route);

        Car car = new Car();
        route.setId(tripDTO.getCarId());
        trip.setCar(car);
        
        trip.setStartTime(tripDTO.getStartTime());
        trip.setEndTime(tripDTO.getEndTime());     
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

    @Override
    public Trip getTripById(int id) {
        Optional<Trip> optional = tripRepository.findTripById(id);
        Trip trip;
        if(optional.isPresent()){
            trip = optional.get();
        }else{
            throw new RuntimeException("Not found");
        }
        return trip;
    }

    @Override  
    public void deleteTripById(int id){
        this.tripRepository.deleteById(id);
    }

    @Override
    public List<Trip> searchByRoute(Route route) {
        List<Trip> trips = tripRepository.findByRoute(route);
        return trips;
    }

    @Override
    public List<Trip> searchByRouteAndCar(Route route, Car car, Boolean isEnable) {
        List<Trip> trips = tripRepository.findByRouteAndCarAndIsEnableOrderByDateAsc(route, car, isEnable);
        return trips;
    }
    
}