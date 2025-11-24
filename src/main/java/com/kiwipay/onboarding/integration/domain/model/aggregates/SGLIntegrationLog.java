package com.kiwipay.onboarding.integration.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Aggregate root for SGL Integration Log.
 * Tracks integration attempts and results for audit purposes.
 */
@Entity
@Table(name = "sgl_integration_logs")
@Data
@NoArgsConstructor
public class SGLIntegrationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sgl_lead_id", nullable = false)
    private Long sglLeadId;

    @Column(name = "onboarding_client_id")
    private Long onboardingClientId;

    @Column(name = "success", nullable = false)
    private Boolean success;

    @Column(name = "error_code")
    private String errorCode;

    @Column(name = "error_message", length = 1000)
    private String errorMessage;

    @Column(name = "processed_at", nullable = false)
    private LocalDateTime processedAt;

    @Column(name = "request_payload", columnDefinition = "TEXT")
    private String requestPayload;

    // Constructor for successful integration
    public SGLIntegrationLog(Long sglLeadId, Long onboardingClientId, String requestPayload) {
        this.sglLeadId = sglLeadId;
        this.onboardingClientId = onboardingClientId;
        this.success = true;
        this.processedAt = LocalDateTime.now();
        this.requestPayload = requestPayload;
    }

    // Constructor for failed integration
    public SGLIntegrationLog(Long sglLeadId, String errorCode, String errorMessage, String requestPayload) {
        this.sglLeadId = sglLeadId;
        this.success = false;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.processedAt = LocalDateTime.now();
        this.requestPayload = requestPayload;
    }
}