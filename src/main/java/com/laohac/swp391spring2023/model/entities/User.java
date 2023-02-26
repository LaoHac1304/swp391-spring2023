package com.laohac.swp391spring2023.model.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.laohac.swp391spring2023.model.Provider;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "user")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Integer id;

    private String email;
    private String username;
    private String password;
    private String fullName;
    private String sex;
    private String phoneNumber;
    private String role;
    @Enumerated(EnumType.STRING)
    private Provider provider;

    private int enabled;

    @Column(name = "verification_code", updatable = false)
    private String verificationCode;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

}
