package com.kiwipay.onboarding.guarantor.application.internal.dto;

import com.kiwipay.onboarding.shared.domain.valueobjects.DocumentType;
import com.kiwipay.onboarding.shared.domain.valueobjects.Gender;
import com.kiwipay.onboarding.shared.domain.valueobjects.MaritalStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class GuarantorResponse {
    private Long id;
    private Long loanId;
    private DocumentType documentType;
    private String documentNumber;
    private BigDecimal monthlyIncome;
    private String firstNames;
    private String lastNames;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private String email;
    private String phone;
    private String districtId;
    private String addressLine1;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}