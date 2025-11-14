package com.kiwipay.onboarding.guarantor.application.internal.dto;

import com.kiwipay.onboarding.guarantor.domain.model.aggregates.GuarantorDocument.ReviewStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GuarantorDocumentReviewRequest {
    @NotNull(message = "Review status is required")
    private ReviewStatus reviewStatus;
}