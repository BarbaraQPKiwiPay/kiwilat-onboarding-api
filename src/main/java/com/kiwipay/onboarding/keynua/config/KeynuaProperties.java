package com.kiwipay.onboarding.keynua.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "keynua")
@Data
public class KeynuaProperties {

    /**
     * Keynua API base URL
     * Environment variable: KEYNUA_API_HOST
     */
    private String apiHost;

    /**
     * Keynua API key for X-api-key header
     * Environment variable: KEYNUA_API_KEY
     */
    private String apiKey;

    /**
     * Keynua authorization token
     * Environment variable: KEYNUA_AUTH_TOKEN
     */
    private String authToken;

    /**
     * Webhook token for validation
     * Environment variable: KEYNUA_WEBHOOK_TOKEN
     */
    private String webhookToken;

    /**
     * Webhook secret for HMAC signature verification
     * Environment variable: KEYNUA_WEBHOOK_SECRET
     */
    private String webhookSecret;

    /**
     * Base URL for constructing sign URLs
     * Environment variable: KEYNUA_SIGNER_BASE_URL
     */
    private String signerBaseUrl;

    /**
     * Keynua WebApp URL for constructing sign links
     * Environment variable: KEYNUA_WEBAPP_URL
     * Example: https://app.stg.keynua.com
     */
    private String webappUrl;

    /**
     * Document template ID with fallback default
     * Environment variable: KEYNUA_DOCUMENT_TEMPLATE
     * Default: kiwi-3d-dnice-prefilled-cavali
     */
    private String documentTemplateId = "kiwi-3d-dnice-prefilled-cavali";

    /**
     * Flag to use mock SGL data provider
     * Default: true
     */
    private boolean useMockSgl = true;
}
