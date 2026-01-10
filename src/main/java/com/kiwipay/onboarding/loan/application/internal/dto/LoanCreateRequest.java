package com.kiwipay.onboarding.loan.application.internal.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanCreateRequest {

    @NotNull(message = "Client ID is required")
    private Long clientId;

    private String clinicBranchId;

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

    @NotNull(message = "Created by user ID is required")
    private Long createdByUserId;
}
