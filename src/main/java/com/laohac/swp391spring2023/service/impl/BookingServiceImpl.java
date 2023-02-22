package com.laohac.swp391spring2023.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laohac.swp391spring2023.model.entities.Seat;
import com.laohac.swp391spring2023.repository.SeatRepository;
import com.laohac.swp391spring2023.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public void chooseSeats(int id) {
        
        Seat seat = seatRepository.findById(id);
        if (!seat.isAvailableSeat()){
            seat.setAvailableSeat(true);
            seatRepository.save(seat);
        }
        throw new UnsupportedOperationException("Unimplemented method 'chooseSeats'");
    }
    
}
