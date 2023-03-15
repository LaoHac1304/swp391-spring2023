package com.laohac.swp391spring2023.service;

import java.util.List;

import com.laohac.swp391spring2023.model.dto.TripDTO;
import com.laohac.swp391spring2023.model.entities.Trip;

public interface TripService {

    public void addTrip(TripDTO tripDTO);

    public List<Trip> getAllTrip();

}
