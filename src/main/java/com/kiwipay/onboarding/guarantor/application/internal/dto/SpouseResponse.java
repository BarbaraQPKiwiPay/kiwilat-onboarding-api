package com.kiwipay.onboarding.guarantor.application.internal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SpouseResponse {
    private Long id;
    private String guarantorId;
    private String documentType;
    private String documentNumber;
    private String firstNames;
    private String lastNames;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SpouseResponse(Long id, String guarantorId, String documentType, String documentNumber,
                          String firstNames, String lastNames, String email, String phone,
                          LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.guarantorId = guarantorId;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.firstNames = firstNames;
        this.lastNames = lastNames;
        this.email = email;
        this.phone = phone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}