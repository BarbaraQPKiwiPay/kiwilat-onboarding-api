package com.kiwipay.onboarding.integration.application.internal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Response DTO for SGL integration endpoint.
 * Provides feedback about the processing result to SGL system.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SGLIntegrationResponse {

    private boolean success;
    private Long onboardingClientId;
    private Long sglLeadId;
    private String message;
    private String errorCode;
    private LocalDateTime processedAt;

    /**
     * Creates a successful response
     */
    public static SGLIntegrationResponse success(Long clientId, Long leadId) {
        SGLIntegrationResponse response = new SGLIntegrationResponse();
        response.setSuccess(true);
        response.setOnboardingClientId(clientId);
        response.setSglLeadId(leadId);
        response.setMessage("Lead processed successfully");
        response.setProcessedAt(LocalDateTime.now());
        return response;
    }

    /**
     * Creates an error response
     */
    public static SGLIntegrationResponse error(Long leadId, String errorMessage) {
        SGLIntegrationResponse response = new SGLIntegrationResponse();
        response.setSuccess(false);
        response.setSglLeadId(leadId);
        response.setMessage(errorMessage);
        response.setErrorCode("PROCESSING_ERROR");
        response.setProcessedAt(LocalDateTime.now());
        return response;
    }

    /**
     * Creates an error response with specific error code
     */
    public static SGLIntegrationResponse error(Long leadId, String errorMessage, String errorCode) {
        SGLIntegrationResponse response = new SGLIntegrationResponse();
        response.setSuccess(false);
        response.setSglLeadId(leadId);
        response.setMessage(errorMessage);
        response.setErrorCode(errorCode);
        response.setProcessedAt(LocalDateTime.now());
        return response;
    }
}