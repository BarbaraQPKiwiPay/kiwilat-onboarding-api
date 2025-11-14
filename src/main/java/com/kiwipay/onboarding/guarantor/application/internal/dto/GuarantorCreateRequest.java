package com.kiwipay.onboarding.guarantor.application.internal.dto;

import com.kiwipay.onboarding.guarantor.domain.model.aggregates.Guarantor.Gender;
import com.kiwipay.onboarding.guarantor.domain.model.aggregates.Guarantor.MaritalStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class GuarantorCreateRequest {
    @NotBlank(message = "Document type is required")
    private String documentType;

    @NotBlank(message = "Document number is required")
    private String documentNumber;

    @NotNull(message = "Monthly income is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Monthly income must be greater than 0")
    private BigDecimal monthlyIncome;

    @NotBlank(message = "First names are required")
    private String firstNames;

    @NotBlank(message = "Last names are required")
    private String lastNames;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotNull(message = "Marital status is required")
    private MaritalStatus maritalStatus;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone is required")
    private String phone;

    @Valid
    @NotNull(message = "Address is required")
    private GuarantorAddressRequest address;
}