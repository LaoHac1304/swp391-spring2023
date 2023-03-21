package com.laohac.swp391spring2023.model.entities;

import java.util.List;

import javax.persistence.*;

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
@Table(name = "carcompany")
public class CarCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CompanyID")
    private int id;
    private String name;
    private String phoneNumber;
    private String address;
    private int businessLicense;
    private String province;
    @OneToMany(mappedBy = "carCompany", cascade = CascadeType.ALL)
    private List<Car> car;
    @Column(name = "img_url")
    private String imgUrl;

    @OneToOne(mappedBy = "carCompany", cascade = CascadeType.ALL)
    private User employee;

}
