package com.kiwipay.onboarding.guarantor.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "guarantor_documents")
public class GuarantorDocument {
    @Id
    private String id;

    @Column(nullable = false, name = "client_id")
    private Long clientId;

    @Column(nullable = false, name = "document_type_id")
    private String documentTypeId;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false, name = "mime_type")
    private String mimeType;

    @Column(nullable = false, name = "size_bytes")
    private Long sizeBytes;

    private String comment;

    @Column(nullable = false, name = "content_base64", columnDefinition = "TEXT")
    private String contentBase64;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "review_status")
    private ReviewStatus reviewStatus;


    @Column(nullable = false, name = "uploaded_at")
    private LocalDateTime uploadedAt;

    @Column(name = "reviewed_at")
    private LocalDateTime reviewedAt;

    public GuarantorDocument() {}

    public GuarantorDocument(String id, Long clientId, String documentTypeId, String filename,
                            String mimeType, Long sizeBytes, String comment, String contentBase64) {
        this.id = id;
        this.clientId = clientId;
        this.documentTypeId = documentTypeId;
        this.filename = filename;
        this.mimeType = mimeType;
        this.sizeBytes = sizeBytes;
        this.comment = comment;
        this.contentBase64 = contentBase64;
        this.reviewStatus = ReviewStatus.PENDING;
        this.uploadedAt = LocalDateTime.now();
    }

    public void updateReviewStatus(ReviewStatus reviewStatus, Object o) {
        this.reviewStatus = reviewStatus;
        this.reviewedAt = LocalDateTime.now();
    }

    public enum ReviewStatus {
        PENDING, APPROVED, REJECTED
    }
}