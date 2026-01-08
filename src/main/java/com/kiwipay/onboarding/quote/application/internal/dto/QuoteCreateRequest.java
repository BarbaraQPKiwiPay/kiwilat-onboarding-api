package com.kiwipay.onboarding.quote.application.internal.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class QuoteCreateRequest {

    @NotBlank(message = "Document type is required")
    private String documentType;

    @NotBlank(message = "Document number is required")
    private String documentNumber;

    @NotNull(message = "Monthly income is required")
    @DecimalMin(value = "0.01", message = "Monthly income must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Monthly income must have at most 10 integer digits and 2 decimal places")
    private BigDecimal monthlyIncome;

    private String branchId; // optional

    // Calculated quote fields (all optional)
    private BigDecimal maf; // Monto a Financiar
    private Integer quotaNumber; // Plazo (número de cuotas)
    private BigDecimal monthlyPayment; // Cuota mensual
    private BigDecimal tea; // Tasa Efectiva Anual
    private BigDecimal tcea; // Tasa de Costo Efectivo Anual
    private Boolean selected; // Si fue seleccionada
}
