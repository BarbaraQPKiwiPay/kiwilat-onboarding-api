package com.kiwipay.onboarding.loan.application.internal.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Request DTO for updating an existing loan
 * All fields are optional - only provided fields will be updated
 */
@Getter
@Setter
public class LoanUpdateRequest {

    private Long clientId;
    private Long clinicalDataId;

    private Double income;
    private Double quotaNumber;
    private Double maf;
    private String group;
    private String segment;
    private String employmentStatus;
    private String classification;
    private Double finalRate;
    private Double experianRate;
    private Double additionalRate;
    private Double initial;

    // User making the update
    @NotNull(message = "Updated by user ID is required")
    private Long updatedByUserId;
}
