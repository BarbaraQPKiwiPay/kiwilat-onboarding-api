package com.kiwipay.onboarding.keynua.domain.services;

import java.util.Map;

/**
 * Keynua Webhook Service Interface
 * Handles processing of webhook events from Keynua
 */
public interface KeynuaWebhookService {

    /**
     * Processes incoming webhook from Keynua
     * Verifies signature and handles event routing
     * 
     * @param headers all HTTP headers from webhook request
     * @param payload raw JSON payload
     */
    void processWebhook(Map<String, String> headers, String payload);
}
