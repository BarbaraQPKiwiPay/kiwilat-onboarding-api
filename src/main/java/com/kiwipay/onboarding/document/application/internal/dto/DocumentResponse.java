package com.kiwipay.onboarding.document.application.internal.dto;

import com.kiwipay.onboarding.document.domain.model.aggregates.Document.DocumentStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DocumentResponse {
    private String id;
    private Long clientId;
    private String documentTypeId;
    private String filename;
    private String mimeType;
    private Long sizeBytes;
    private String comment;
    private DocumentStatus status;
    private LocalDateTime createdAt;
}