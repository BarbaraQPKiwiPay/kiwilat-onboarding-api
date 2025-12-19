package com.kiwipay.onboarding.authentication.application.internal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> roles;

    public AuthenticationResponse(String token, String username, String firstName, 
                               String lastName, String email, List<String> roles) {
        this.token = token;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
    }
}