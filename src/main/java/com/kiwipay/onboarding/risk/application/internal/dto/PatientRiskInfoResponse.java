package com.kiwipay.onboarding.risk.application.internal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PatientRiskInfoResponse {
    private String lastNames;
    private String firstNames;
    private String documentNumber;
    private LocalDate birthDate;
    private Integer age;
    private String email;
    private String phone;
    private BigDecimal monthlyIncome;
    
    // Experian fields
    private String experianClassification;
    private String experianSegment;
    private String experianGroup;
    private Integer experianScore;
    private String experianResponse;
    private String experianResult;
}