package com.kiwipay.onboarding.loan.application.internal.dto;

import com.kiwipay.onboarding.loan.domain.model.valueobjects.LoanStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Request DTO for changing loan status
 */
@Getter
@Setter
public class LoanStatusChangeRequest {

    @NotNull(message = "New status is required")
    private LoanStatus newStatus;

    @NotNull(message = "User ID is required")
    private Long userId;

    private String reason;
}
