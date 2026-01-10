package com.kiwipay.onboarding.document.domain.model.aggregates;

import com.kiwipay.onboarding.document.domain.model.valueobjects.DocumentOwnerType;
import com.kiwipay.onboarding.document.domain.model.valueobjects.DocumentStatus;
import com.kiwipay.onboarding.document.domain.model.valueobjects.ReviewStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "documents")
public class Document {
    @Id
    private String id;

    @Column(name = "loan_id", nullable = false)
    private Long loanId;

    @Enumerated(EnumType.STRING)
    @Column(name = "owner_type", nullable = false)
    private DocumentOwnerType ownerType;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "guarantor_id")
    private Long guarantorId;

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
    @Column(nullable = false)
    private DocumentStatus status;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "review_status")
    private ReviewStatus reviewStatus;

    @Column(name = "reviewed_at")
    private LocalDateTime reviewedAt;

    public Document() {
    }

    public Document(String id, Long loanId, DocumentOwnerType ownerType, Long clientId, Long guarantorId,
            String documentTypeId, String filename, String mimeType, Long sizeBytes,
            String comment, String contentBase64) {
        this.id = id;
        this.loanId = loanId;
        this.ownerType = ownerType;
        this.clientId = clientId;
        this.guarantorId = guarantorId;
        this.documentTypeId = documentTypeId;
        this.filename = filename;
        this.mimeType = mimeType;
        this.sizeBytes = sizeBytes;
        this.comment = comment;
        this.contentBase64 = contentBase64;
        this.status = DocumentStatus.READY;
        this.reviewStatus = ReviewStatus.PENDING;
        this.createdAt = LocalDateTime.now();
        validate();
    }

    @PrePersist
    @PreUpdate
    public void validate() {
        if (loanId == null) {
            throw new IllegalArgumentException("Loan ID cannot be null");
        }

        if (ownerType == null) {
            throw new IllegalArgumentException("Owner type cannot be null");
        }

        // Validate FK consistency based on ownerType
        if (ownerType == DocumentOwnerType.CLIENT) {
            if (clientId == null) {
                throw new IllegalArgumentException("Client ID is required when owner type is CLIENT");
            }
            if (guarantorId != null) {
                throw new IllegalArgumentException("Guarantor ID must be null when owner type is CLIENT");
            }
        } else if (ownerType == DocumentOwnerType.GUARANTOR) {
            if (guarantorId == null) {
                throw new IllegalArgumentException("Guarantor ID is required when owner type is GUARANTOR");
            }
            if (clientId != null) {
                throw new IllegalArgumentException("Client ID must be null when owner type is GUARANTOR");
            }
        }
    }

    public void updateReviewStatus(ReviewStatus reviewStatus, String comment) {
        this.reviewStatus = reviewStatus;
        if (comment != null && !comment.trim().isEmpty()) {
            this.comment = comment;
        }
        this.reviewedAt = LocalDateTime.now();
    }
}