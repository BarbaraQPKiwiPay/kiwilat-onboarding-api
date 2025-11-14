package com.kiwipay.onboarding.document.domain.model.aggregates;

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
    @Column(nullable = false)
    private DocumentStatus status;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    // Review fields
    @Enumerated(EnumType.STRING)
    @Column(name = "review_status")
    private ReviewStatus reviewStatus;

    @Column(name = "reviewed_at")
    private LocalDateTime reviewedAt;

    public Document() {}

    public Document(String id, Long clientId, String documentTypeId, String filename, 
                   String mimeType, Long sizeBytes, String comment, String contentBase64) {
        this.id = id;
        this.clientId = clientId;
        this.documentTypeId = documentTypeId;
        this.filename = filename;
        this.mimeType = mimeType;
        this.sizeBytes = sizeBytes;
        this.comment = comment;
        this.contentBase64 = contentBase64;
        this.status = DocumentStatus.READY;
        this.createdAt = LocalDateTime.now();
    }

    // Method to update review status
    public void updateReviewStatus(ReviewStatus reviewStatus) {
        this.reviewStatus = reviewStatus;
        this.reviewedAt = LocalDateTime.now();
    }

    public enum DocumentStatus {
        READY, PROCESSING, REJECTED
    }

    public enum ReviewStatus {
        PENDING, APPROVED, REJECTED
    }
}