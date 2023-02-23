package com.laohac.swp391spring2023.model.dto;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;


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
public class RouteDTORequest {

    private String state1;
    private String state2;
    // @DateTimeFormat(pattern="yyyy/MM/dd")
    // private Date date;
    private String date;
}
