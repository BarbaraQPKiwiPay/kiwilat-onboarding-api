package com.kiwipay.onboarding.guarantor.application.internal.dto;

import com.kiwipay.onboarding.guarantor.domain.model.aggregates.GuarantorDocument.ReviewStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class GuarantorDocumentResponse {
    private String id;
    private Long clientId;
    private String documentTypeId;
    private String filename;
    private String mimeType;
    private Long sizeBytes;
    private String comment;
    private ReviewStatus reviewStatus;
    private LocalDateTime uploadedAt;
    private LocalDateTime reviewedAt;
}