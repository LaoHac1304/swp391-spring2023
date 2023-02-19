package com.laohac.swp391spring2023.model.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonFormat;

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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @Column(name = "EndTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    @ManyToOne
    @JoinColumn(name = "CarID")
    private Car car;
    private int price;
}
