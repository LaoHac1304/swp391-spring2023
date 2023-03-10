package com.laohac.swp391spring2023.config;

import java.util.Date;

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

        // Trip trip = tripRepository.findById(12);
        // Car car = trip.getCar();
        // for (int i=1;i<=20;i++){
        
        //     Seat seat = Seat.builder()
        //                     .seatNumber(i)
        //                     .car(car)
        //                     .availableSeat(0)
        //                     .trip(trip)
        //                     .build();
        //     seatRepository.save(seat);
        // }

        
        
    }
    
    
}
