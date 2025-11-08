package com.kiwipay.onboarding.titular.application.internal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientSummaryResponse {
    private Long id;
    private String documentType;
    private String documentNumber;
    private String firstNames;
    private String lastNames;
    private String gender;
}