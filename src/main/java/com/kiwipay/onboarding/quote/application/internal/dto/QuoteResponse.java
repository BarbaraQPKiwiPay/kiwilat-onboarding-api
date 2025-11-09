package com.kiwipay.onboarding.quote.application.internal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
public class QuoteResponse {
    private Long id;
    private String documentType;
    private String documentNumber;
    private BigDecimal monthlyIncome;
    private String branchId;
    private Long clientId;

}
