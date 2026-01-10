package com.kiwipay.onboarding.document.application.internal.dto;

import com.kiwipay.onboarding.document.domain.model.valueobjects.DocumentOwnerType;
import com.kiwipay.onboarding.document.domain.model.valueobjects.DocumentStatus;
import com.kiwipay.onboarding.document.domain.model.valueobjects.DocumentType;
import com.kiwipay.onboarding.document.domain.model.valueobjects.ReviewStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DocumentResponse {
    private String id;
    private Long loanId;
    private DocumentOwnerType ownerType;
    private Long clientId;
    private Long guarantorId;
    private DocumentType documentType;
    private String filename;
    private String mimeType;
    private Long sizeBytes;
    private String comment;
    private DocumentStatus status;
    private LocalDateTime createdAt;
    private ReviewStatus reviewStatus;
    private LocalDateTime reviewedAt;
}