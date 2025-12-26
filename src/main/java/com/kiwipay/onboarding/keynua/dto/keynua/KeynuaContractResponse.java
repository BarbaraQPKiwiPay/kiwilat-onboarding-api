package com.kiwipay.onboarding.keynua.dto.keynua;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Response from Keynua API after contract creation/retrieval
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeynuaContractResponse {

    // Keynua returns 'id' but we use 'contractId' internally
    @JsonProperty("id")
    private String contractId;

    private String accountId;
    private String templateId;
    private String shortCode;
    private String signerToken;
    private String status;

    // Users array contains the token for each signer
    private List<KeynuaUserResponse> users;

    // Add other fields as returned by Keynua API
    private String createdAt;
    private String updatedAt;

    /**
     * Nested class representing each user/signer in the contract
     */
    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class KeynuaUserResponse {
        private Integer id;
        private String name;
        private String email;
        private String phone;
        private List<String> groups;
        private String token; // The signer token is HERE
        private String state; // pending, working, done
    }
}
