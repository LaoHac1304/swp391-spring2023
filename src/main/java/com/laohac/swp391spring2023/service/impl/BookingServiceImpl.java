package com.laohac.swp391spring2023.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laohac.swp391spring2023.model.dto.CheckOutInfoDTOReponse;
import com.laohac.swp391spring2023.model.dto.UserDTOResponse;
import com.laohac.swp391spring2023.model.entities.Car;
import com.laohac.swp391spring2023.model.entities.OrderDetail;
import com.laohac.swp391spring2023.model.entities.Seat;
import com.laohac.swp391spring2023.model.entities.Trip;
import com.laohac.swp391spring2023.model.entities.User;
import com.laohac.swp391spring2023.repository.OrderDetailRepository;
import com.laohac.swp391spring2023.repository.SeatRepository;
import com.laohac.swp391spring2023.repository.UserRepository;
import com.laohac.swp391spring2023.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  OrderDetailRepository orderDetailRepository;

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
        BigDecimal priceTotal = price.multiply(BigDecimal.valueOf(listSeats.size()));
        // int priceTotal = price*(listSeats.size());
        return CheckOutInfoDTOReponse.builder().trip(trip).lSeats(listSeats).priceTotal(priceTotal).user(userDTOResponse).build();
        
    }

    @Override
    public void saveOrder(HttpSession session) {
       
        CheckOutInfoDTOReponse checkOutInfoDTOReponse = (CheckOutInfoDTOReponse) session.getAttribute("checkoutinfo");
        
        User user = (userRepository.findByEmail(checkOutInfoDTOReponse.getUser().getEmail())).get();
        Trip trip = checkOutInfoDTOReponse.getTrip();
        Car car = checkOutInfoDTOReponse.getTrip().getCar();
        int quantity = checkOutInfoDTOReponse.getLSeats().size();
        Date date = new Date();
        String email = user.getEmail();
        String fullName = user.getFullName();
        String phoneNumber = user.getPhoneNumber();
        BigDecimal total = checkOutInfoDTOReponse.getPriceTotal();
        String departure = trip.getArrivalDetail();
        String arrival = trip.getArrivalDetail();
        String listSeats = checkOutInfoDTOReponse.getLSeats().toString();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail = OrderDetail
                            .builder()
                            .customer(user)
                            .trip(trip)
                            .car(car)
                            .quantity(quantity)
                            .date(null)
                            .email(email)
                            .fullName(fullName)
                            .phoneNumber(phoneNumber)
                            .total(total)
                            .departure(departure)
                            .arrival(arrival)
                            .listSeats(listSeats)
                            .build();
        orderDetailRepository.save(orderDetail);
        System.out.println("ahihi");
        

    }
    
}
