package com.kiwipay.onboarding.keynua.dto.keynua;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Webhook event payload from Keynua
 * Matches the actual structure sent by Keynua API
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebhookEvent {

    /**
     * Event type: "ContractItemUpdated", "ContractFinished", "ContractSigned", etc.
     * Keynua sends this as "type" field
     */
    @JsonProperty("type")
    private String eventType;

    /**
     * Account ID
     */
    private String accountId;

    /**
     * Event-specific data payload containing contractId and other data
     */
    private WebhookPayload payload;

    /**
     * Nested payload structure from Keynua
     */
    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WebhookPayload {
        private String contractId;
        private String status;
        private String signedAt;
        // Add other fields as needed
    }

    /**
     * Convenience method to get contractId from nested payload
     */
    public String getContractId() {
        return payload != null ? payload.getContractId() : null;
    }

    /**
     * Convenience method to get status from nested payload
     */
    public String getStatus() {
        return payload != null ? payload.getStatus() : null;
    }
}
