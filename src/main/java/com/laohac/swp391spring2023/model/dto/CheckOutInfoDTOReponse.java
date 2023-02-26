package com.laohac.swp391spring2023.model.dto;

import java.math.BigDecimal;
import java.util.List;

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
    private Trip trip;
    private BigDecimal priceTotal;
}
