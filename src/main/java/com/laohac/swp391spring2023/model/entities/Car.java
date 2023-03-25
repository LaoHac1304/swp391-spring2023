package com.laohac.swp391spring2023.model.entities;

import java.util.ArrayList;
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
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CarID")
    private int id;
    private int capacity;
    @ManyToOne
    @JoinColumn(name = "CompanyID")
    private CarCompany carCompany;
    private String plateNumber;
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Trip> trip;     

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Seat> seats;

    public void initSeats(Trip trip) {
        this.seats = new ArrayList<>();
        for (int i = 1; i <= this.capacity; i++) {
            Seat seat = new Seat();
            seat.setSeatNumber(i);
            seat.setCar(this);
            seat.setTrip(trip);
            this.seats.add(seat);
        }
    }
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
}
