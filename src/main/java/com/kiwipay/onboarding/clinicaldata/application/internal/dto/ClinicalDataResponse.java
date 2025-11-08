package com.kiwipay.onboarding.titular.application.internal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ClinicalDataResponse {
    private String id;
    private Long clientId;
    private String medicalCategoryId;
    private String clinicId;
    private String branchId;
    private String createdAt;
    private String updatedAt;
}