package com.laohac.swp391spring2023.model.dto;

import lombok.*;

import java.math.BigDecimal;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfitDTOReponse {
    
    private BigDecimal totalPrice;
    private String month;
    private int totalTicket;

}
