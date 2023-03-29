package com.laohac.swp391spring2023.config;

import java.util.Date;
import java.util.List;

import com.laohac.swp391spring2023.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.laohac.swp391spring2023.model.entities.Car;
import com.laohac.swp391spring2023.model.entities.Seat;
import com.laohac.swp391spring2023.model.entities.Trip;
import com.laohac.swp391spring2023.repository.SeatRepository;
import com.laohac.swp391spring2023.repository.TripRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private TripRepository tripRepository;

    @Override
    public void run(String... args) throws Exception {

        // Trip trip = tripRepository.findById(125);
        // Car car = trip.getCar();
        // for (int i=1;i<=68;i++){
        
        //     Seat seat = Seat.builder()
        //                     .seatNumber(i)
        //                     .car(car)
        //                     .availableSeat(0)
        //                     .trip(trip)
        //                     .build();
        //     seatRepository.save(seat);
        // }

        List<Trip> trips = tripRepository.findAll();
        for (Trip trip:trips) {
            trip.setIsSpecialDay(Utils.isSpecialDay(trip.getDate()));
            tripRepository.save(trip);
        }
    }
    
    
}
