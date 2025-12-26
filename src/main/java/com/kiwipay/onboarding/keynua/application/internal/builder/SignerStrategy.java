package com.kiwipay.onboarding.keynua.application.internal.builder;

import com.kiwipay.onboarding.keynua.dto.keynua.KeynuaContractRequest;

/**
 * Strategy interface for building Keynua contract payloads
 */
public interface SignerStrategy {

    /**
     * Builds the Keynua contract request payload
     * 
     * @param context the payload builder context with all required data
     * @return complete Keynua contract request
     */
    KeynuaContractRequest buildPayload(PayloadBuilderContext context);
}
