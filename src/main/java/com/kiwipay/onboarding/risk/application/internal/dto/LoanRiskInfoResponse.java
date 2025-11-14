package com.kiwipay.onboarding.risk.application.internal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class LoanRiskInfoResponse {
    private String campaign;
    private String loanStatus;
    private LocalDate applicationDate;
    private Integer installments;
    private BigDecimal monthlyInstallment;
    private BigDecimal requestedAmount;
}