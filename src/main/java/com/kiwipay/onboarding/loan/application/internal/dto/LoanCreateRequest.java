package com.kiwipay.onboarding.loan.application.internal.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Request DTO for creating a new loan
 */
@Getter
@Setter
public class LoanCreateRequest {

    @NotNull(message = "Client ID is required")
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

    // User making the creation
    @NotNull(message = "Created by user ID is required")
    private Long createdByUserId;
}
