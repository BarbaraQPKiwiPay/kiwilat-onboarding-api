package com.kiwipay.onboarding.authentication.application.internal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean enabled;
    private List<String> roles;
    private String createdAt;
    private String updatedAt;
}