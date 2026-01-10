package com.kiwipay.onboarding.document.application.internal.dto;

import com.kiwipay.onboarding.document.domain.model.valueobjects.ReviewStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DocumentReviewRequest {
    private ReviewStatus reviewStatus;
    private String comment;
}