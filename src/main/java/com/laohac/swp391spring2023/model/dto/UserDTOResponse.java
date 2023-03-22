package com.laohac.swp391spring2023.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTOResponse {

    private String email;
    private String username;
    private String fullName;
    private String sex;
    private String phoneNumber;
    private String role;
    private int enabled;
    private String verificationCode;
    private int carCompanyId; 
    private String provider;
    
}
