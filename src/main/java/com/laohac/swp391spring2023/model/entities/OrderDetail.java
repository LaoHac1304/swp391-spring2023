package com.laohac.swp391spring2023.model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.laohac.swp391spring2023.model.PaymentStatus;
import com.laohac.swp391spring2023.model.PaymentType;
import com.laohac.swp391spring2023.model.Status;

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
    private String email;
    private String fullName;
    private String phoneNumber;
    @Column(name = "Total")
    private BigDecimal total;
    private String departure;
    private String arrival;
    @Column(name = "List_Seats_Id")
    private String listSeats;
    @Column(name = "List_Seats_Number")
    private String listSeatsNumber;
    @Column(name = "Payment_Type")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @Column(name = "Payment_Status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @Column(name = "Status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;
    @Column(name = "UpdatedAt")
    private LocalDateTime updatedAt;


}
