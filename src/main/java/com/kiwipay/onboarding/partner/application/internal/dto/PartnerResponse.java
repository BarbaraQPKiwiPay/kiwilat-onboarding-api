package com.kiwipay.onboarding.partner.application.internal.dto;

import com.kiwipay.onboarding.shared.domain.valueobjects.DocumentType;
import com.kiwipay.onboarding.partner.domain.model.valueobjects.PartnerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

/**
 * Response DTO for spouse information
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartnerResponse {

    private Long id;
    private Long loanId;
    private PartnerType spouseType;

    // Conditional foreign keys
    private Long clientId;
    private String guarantorId;
    private Long patientId;

    // Personal information
    private DocumentType documentType;
    private String documentNumber;
    private String firstNames;
    private String lastNames;
    private String email;
    private String phone;

    // Audit fields
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
