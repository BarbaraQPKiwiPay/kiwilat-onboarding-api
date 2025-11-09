package com.kiwipay.onboarding.quote.application.internal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
public class QuoteCreateRequest {
    private String documentType;
    private String documentNumber;
    private BigDecimal monthlyIncome;
    private String branchId; // optional

}
