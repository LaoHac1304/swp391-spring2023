package com.laohac.swp391spring2023.model.entities;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.laohac.swp391spring2023.utils.Utils;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

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
@Entity
@Table(name = "tripinfor")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TripID")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "RouteID")
    private Route route;
    @Column(name = "StartTime")
    @JsonFormat(pattern="HH:mm:ss", shape = Shape.STRING)
    private String startTime;
    @Column(name = "EndTime")
    @JsonFormat(pattern="HH:mm:ss", shape = Shape.STRING)
    private String endTime;
    @ManyToOne
    @JoinColumn(name = "CarID")
    private Car car;
    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;
    private String departureDetail;
    private String arrivalDetail;
    @Column(name = "Day")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<Seat> listAvailableSeats;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
    @Column(name = "Is_Special_Day")
    private Boolean isSpecialDay = Utils.isSpecialDay(date);

    private int totalAvailableSeat;

    @Column(name = "IsEnable")
    private Boolean isEnable;

    @Column(name = "isBiggestDay")
    private Boolean isBiggestDay;
   
}   
