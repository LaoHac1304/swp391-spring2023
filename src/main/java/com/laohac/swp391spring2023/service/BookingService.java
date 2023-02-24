package com.laohac.swp391spring2023.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.laohac.swp391spring2023.model.dto.CheckOutInfoDTOReponse;

public interface BookingService {

    public void chooseSeats(int id);

    public CheckOutInfoDTOReponse showCheckOutInfo(List<Integer> listSeats, HttpSession session);
    
}
