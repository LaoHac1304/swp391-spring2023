package com.laohac.swp391spring2023.model.dto;

import com.laohac.swp391spring2023.model.entities.CarCompany;

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
public class MemberDTOReponse {


    private Integer id;
    private String email;
    private String username;
    private String password;
    private String fullName;
    private String sex;
    private String phoneNumber;
    private String role;
    private CarCompany carCompany;
    private Boolean enableToWork;
    private String name;
    
}
