package com.laohac.swp391spring2023.model.dto;

<<<<<<< HEAD
=======
import java.math.BigDecimal;
>>>>>>> 46e05d6f4ed2b1d01970301f7845e877c0fc6017
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
<<<<<<< HEAD
    private int priceTotal;
=======
    private BigDecimal priceTotal;
>>>>>>> 46e05d6f4ed2b1d01970301f7845e877c0fc6017
}
