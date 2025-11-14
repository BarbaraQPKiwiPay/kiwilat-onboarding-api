package com.kiwipay.onboarding.guarantor.application.internal.dto;

import com.kiwipay.onboarding.guarantor.domain.model.aggregates.Guarantor.Gender;
import com.kiwipay.onboarding.guarantor.domain.model.aggregates.Guarantor.MaritalStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class GuarantorResponse {
    private String guarantorId;
    private Long clientId;
    private String documentType;
    private String documentNumber;
    private BigDecimal monthlyIncome;
    private String firstNames;
    private String lastNames;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private String email;
    private String phone;
    private GuarantorAddressResponse address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}