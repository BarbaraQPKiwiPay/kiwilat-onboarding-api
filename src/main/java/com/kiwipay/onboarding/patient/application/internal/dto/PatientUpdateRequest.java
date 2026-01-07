package com.kiwipay.onboarding.patient.application.internal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientUpdateRequest {
    private String documentType;
    private String documentNumber;
    private String firstNames;
    private String lastNames;
    private String gender;
    private String phone;
    private String email;
    private String districtId;
    private String addressLine1;
}