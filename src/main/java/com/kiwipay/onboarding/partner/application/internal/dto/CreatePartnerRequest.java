package com.kiwipay.onboarding.partner.application.internal.dto;

import com.kiwipay.onboarding.shared.domain.valueobjects.DocumentType;
import com.kiwipay.onboarding.partner.domain.model.valueobjects.PartnerType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for creating a new spouse
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePartnerRequest {

    @NotNull(message = "Loan ID is required")
    private Long loanId;

    @NotNull(message = "Partner type is required")
    private PartnerType partnerType;

    // Conditional foreign keys - only ONE should be provided based on partnerType
    private Long clientId;
    private String guarantorId;
    private Long patientId;

    @NotNull(message = "Document type is required")
    private DocumentType documentType;

    @NotBlank(message = "Document number is required")
    private String documentNumber;

    @NotBlank(message = "First names are required")
    private String firstNames;

    @NotBlank(message = "Last names are required")
    private String lastNames;

    private String email;
    private String phone;
}
