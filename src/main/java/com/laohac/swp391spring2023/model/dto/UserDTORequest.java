package com.laohac.swp391spring2023.model.dto;

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
public class UserDTORequest {
    
    private String username;
    private String password;
    private String email;
    private String role;
}
