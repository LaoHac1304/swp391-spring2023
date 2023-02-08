package com.laohac.swp391spring2023.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "employee")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmpID")
    private int id;

    @Column(name = "Role")
    private String role;

    @Column(name = "FullName")
    private String name;
    
    @Column(name = "PhoneNumber", unique = true)
    private String phoneNumber;

    @Column(name = "UserName", unique = true)
    private String username;

    @Column(name = "Password")
    private String password;

    
}
