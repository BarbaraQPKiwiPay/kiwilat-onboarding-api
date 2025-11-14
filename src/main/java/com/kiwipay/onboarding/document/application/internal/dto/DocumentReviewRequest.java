package com.kiwipay.onboarding.document.application.internal.dto;

import com.kiwipay.onboarding.document.domain.model.aggregates.Document.ReviewStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DocumentReviewRequest {
    @NotNull(message = "Review status is required")
    private ReviewStatus reviewStatus;
}