package com.laohac.swp391spring2023.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laohac.swp391spring2023.model.dto.CheckOutInfoDTOReponse;
import com.laohac.swp391spring2023.model.dto.UserDTOResponse;
import com.laohac.swp391spring2023.model.entities.Seat;
import com.laohac.swp391spring2023.model.entities.Trip;
import com.laohac.swp391spring2023.repository.SeatRepository;
import com.laohac.swp391spring2023.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public void chooseSeats(int id) {
        
        Seat seat = seatRepository.findById(id);
        if (seat.getAvailableSeat() == 0){
            seat.setAvailableSeat(1);
            seatRepository.save(seat);
        }
        return;    
    }

    @Override
    public CheckOutInfoDTOReponse showCheckOutInfo(List<Integer> listSeats, HttpSession session) {
        
        if (listSeats.isEmpty()) return null;
        UserDTOResponse userDTOResponse = (UserDTOResponse) session.getAttribute("userSession");
        int id = listSeats.get(0);
        Seat seat = seatRepository.findById(id);
        Trip trip = seat.getTrip();
        BigDecimal price = trip.getPrice();
        int priceTotal = price.multiply(BigDecimal.valueOf(listSeats.size())).intValue();
        // int priceTotal = price*(listSeats.size());
        return CheckOutInfoDTOReponse.builder().trip(trip).lSeats(listSeats).priceTotal(priceTotal).user(userDTOResponse).build();
        
    }
    
}
