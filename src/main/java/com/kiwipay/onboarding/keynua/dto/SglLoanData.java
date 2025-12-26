package com.kiwipay.onboarding.keynua.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * SGL Loan Data DTO
 * Contains loan/quote information from SGL system
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SglLoanData {

    private String loanId;
    private BigDecimal amount;
    private Integer termMonths;
    private BigDecimal maf;
    private BigDecimal monthlyPayment;
    private BigDecimal interestRate;
    private String clientId;

    // Add other fields as needed from SGL
}
