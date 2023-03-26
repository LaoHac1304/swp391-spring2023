package com.laohac.swp391spring2023.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import com.laohac.swp391spring2023.model.Status;
import com.laohac.swp391spring2023.model.dto.CheckOutInfoDTOReponse;
import com.laohac.swp391spring2023.model.entities.OrderDetail;

public interface BookingService {

    public void chooseSeats(int id);

    public CheckOutInfoDTOReponse showCheckOutInfo(List<Integer> listSeats, HttpSession session);

    public void saveOrder(HttpSession session, boolean isSaved);

    public void sendVerificationEmail(HttpSession session, String siteURL) throws UnsupportedEncodingException, MessagingException;

    public boolean cancelBooking(int bookingId);

    public void cancelSeat(int id);

    public List<OrderDetail> getAllBookings();

    public List<OrderDetail> getBookingsByStatus(Status bookingStatus);
    
}
