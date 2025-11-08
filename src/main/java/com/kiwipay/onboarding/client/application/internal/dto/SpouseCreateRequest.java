package com.kiwipay.onboarding.client.application.internal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SpouseCreateRequest {
    private String documentType;
    private String documentNumber;
    private String firstNames;
    private String lastNames;
    private String email;
    private String phone;
}