package com.laohac.swp391spring2023.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.laohac.swp391spring2023.model.entities.Car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TripDTO {
    private int routeId;
    private String startTime;
    private String endTime;
    private int carId;
    private BigDecimal price;
    private String departureDetail;
    private String arrivalDetail;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
}
