package com.kiwipay.onboarding.authentication.application.internal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserRegistrationRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> roles;
}