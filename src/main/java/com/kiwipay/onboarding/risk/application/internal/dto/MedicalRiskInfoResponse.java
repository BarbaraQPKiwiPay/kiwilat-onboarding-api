package com.kiwipay.onboarding.risk.application.internal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MedicalRiskInfoResponse {
    private String medicalCategory;
    private String medicalCenter;
    private String branch;
}