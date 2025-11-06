package com.kiwipay.onboarding.titular.application.internal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ClinicalDataCreateRequest {
    private String medicalCategoryId;
    private String clinicId;
    private String branchId;
    private BigDecimal monthlyIncome;
    private String currency = "PEN";
}