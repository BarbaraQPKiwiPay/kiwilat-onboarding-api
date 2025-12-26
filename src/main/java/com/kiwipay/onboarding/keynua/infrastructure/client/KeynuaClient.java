package com.kiwipay.onboarding.keynua.infrastructure.client;

import com.kiwipay.onboarding.keynua.dto.keynua.KeynuaContractRequest;
import com.kiwipay.onboarding.keynua.dto.keynua.KeynuaContractResponse;

/**
 * Keynua HTTP Client Interface
 * Handles communication with Keynua API
 */
public interface KeynuaClient {

    /**
     * Creates a new contract in Keynua
     * 
     * @param request the contract request payload
     * @return Keynua contract response
     */
    KeynuaContractResponse createContract(KeynuaContractRequest request);

    /**
     * Retrieves an existing contract from Keynua
     * 
     * @param contractId the contract ID
     * @return Keynua contract response
     */
    KeynuaContractResponse getContract(String contractId);
}
