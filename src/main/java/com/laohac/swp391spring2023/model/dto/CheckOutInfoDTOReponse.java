package com.laohac.swp391spring2023.model.dto;

import java.math.BigDecimal;
import java.util.List;

import com.laohac.swp391spring2023.model.PaymentStatus;
import com.laohac.swp391spring2023.model.PaymentType;
import com.laohac.swp391spring2023.model.Status;
import com.laohac.swp391spring2023.model.entities.Trip;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckOutInfoDTOReponse {

    private UserDTOResponse user;
    private List<Integer> lSeats;
    private List<Integer> lSeatsNumber;
    private Trip trip;
    private BigDecimal priceTotal;
    private PaymentStatus paymentStatus;
    private PaymentType paymentType;
    private Status status;
    private boolean isSpecialDay;
    private String fullName;
    private String phoneNumber;
    private String email;
    private List<String> listSeatNumber;
}
