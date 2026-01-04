package com.kiwipay.onboarding.keynua.dto.keynua;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Main Keynua contract request payload
 * This matches the exact structure expected by Keynua API
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KeynuaContractRequest {

    private String templateId;
    private String title;
    private String description;
    private String language;
    private List<KeynuaUser> users;
    private List<KeynuaPrefilledItem> prefilledItems;
    private KeynuaFlags flags;
    private Boolean userEmailNotification;

    // For multiple signers only
    private Integer expirationInHours;
    private Boolean disableNotification;

    // Documents with name and base64 content (as per legacy flow)
    private List<KeynuaDocument> documents;
}
