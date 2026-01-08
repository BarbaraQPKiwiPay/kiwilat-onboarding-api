package com.kiwipay.onboarding.quote.application.internal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
public class QuoteResponse {
    private Long id;
    private Long loanId;
    private String documentType;
    private String documentNumber;
    private BigDecimal monthlyIncome;
    private String branchId;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
