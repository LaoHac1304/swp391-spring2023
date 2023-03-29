package com.laohac.swp391spring2023.service;

import java.util.List;

import com.laohac.swp391spring2023.model.dto.TripDTO;
import com.laohac.swp391spring2023.model.entities.Car;
import com.laohac.swp391spring2023.model.entities.Route;
import com.laohac.swp391spring2023.model.entities.Trip;

public interface TripService {

    public void addTrip(TripDTO tripDTO);

    public List<Trip> getAllTrip();

    public Trip getTripById(int id);

    public void deleteTripById(int id);
    
    public List<Trip> searchByRoute(Route route);

    public List<Trip> searchByRouteAndCar(Route route, Car car, Boolean isEnable);

}
