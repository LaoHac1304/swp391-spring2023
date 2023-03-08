package com.laohac.swp391spring2023.model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Entity
@Table(name = "booking")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookingID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "CustomerID")
    private User customer; // many to one

    @ManyToOne
    @JoinColumn(name = "TripID")
    private Trip trip; // many to one

    @ManyToOne
    @JoinColumn(name = "CarID")
    private Car car; // many to one
    
    private int quantity;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

    private String email;
    private String fullName;
    private String phoneNumber;
    private BigDecimal total;
    private String departure;
    private String arrival;
    private String listSeats;
    private String listSeatsNumber;
    
}
