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

    // Calculated quote fields
    private BigDecimal maf; // Monto a Financiar
    private Integer quotaNumber; // Plazo (número de cuotas)
    private BigDecimal monthlyPayment; // Cuota mensual
    private BigDecimal tea; // Tasa Efectiva Anual
    private BigDecimal tcea; // Tasa de Costo Efectivo Anual
    private Boolean selected; // Si fue seleccionada
}
