package com.laohac.swp391spring2023.service;

<<<<<<< HEAD
import java.util.List;

=======
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
>>>>>>> 46e05d6f4ed2b1d01970301f7845e877c0fc6017
import javax.servlet.http.HttpSession;

import com.laohac.swp391spring2023.model.dto.CheckOutInfoDTOReponse;

public interface BookingService {

    public void chooseSeats(int id);

    public CheckOutInfoDTOReponse showCheckOutInfo(List<Integer> listSeats, HttpSession session);
<<<<<<< HEAD
=======

    public void saveOrder(HttpSession session);

    public void sendVerificationEmail(HttpSession session, String siteURL) throws UnsupportedEncodingException, MessagingException;
>>>>>>> 46e05d6f4ed2b1d01970301f7845e877c0fc6017
    
}
