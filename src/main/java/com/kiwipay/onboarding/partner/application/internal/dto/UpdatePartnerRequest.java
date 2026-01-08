package com.kiwipay.onboarding.partner.application.internal.dto;

import com.kiwipay.onboarding.shared.domain.valueobjects.DocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePartnerRequest {

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
