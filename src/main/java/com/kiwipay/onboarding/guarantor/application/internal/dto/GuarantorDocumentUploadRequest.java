package com.kiwipay.onboarding.guarantor.application.internal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GuarantorDocumentUploadRequest {
    @NotBlank(message = "Document type ID is required")
    private String documentTypeId;

    @NotBlank(message = "Filename is required")
    private String filename;

    @NotBlank(message = "MIME type is required")
    private String mimeType;

    @NotNull(message = "Size in bytes is required")
    @Positive(message = "Size must be positive")
    private Long sizeBytes;

    private String comment;

    @NotBlank(message = "Base64 content is required")
    private String contentBase64;
}