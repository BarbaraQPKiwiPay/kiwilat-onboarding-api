package com.kiwipay.onboarding.titular.application.internal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SpouseResponse {
    private Long id;
    private Long clientId;
    private String documentType;
    private String documentNumber;
    private String firstNames;
    private String lastNames;
    private String email;
    private String phone;
    private String createdAt;
}