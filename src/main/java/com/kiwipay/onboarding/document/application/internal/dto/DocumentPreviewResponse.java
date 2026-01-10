package com.kiwipay.onboarding.document.application.internal.dto;

import com.kiwipay.onboarding.document.domain.model.valueobjects.DocumentOwnerType;
import com.kiwipay.onboarding.document.domain.model.valueobjects.DocumentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DocumentPreviewResponse {
    private String id;
    private Long loanId;
    private DocumentOwnerType ownerType;
    private DocumentType documentType;
    private String filename;
    private String mimeType;
    private Long sizeBytes;
    private String comment;
    private String contentBase64;
    private LocalDateTime createdAt;
}
