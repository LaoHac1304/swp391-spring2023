package com.laohac.swp391spring2023.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaypalUserInfo {

    private String fullName;
    private Date date;
    private BigDecimal price;
    
}
