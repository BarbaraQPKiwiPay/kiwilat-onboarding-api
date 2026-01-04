package com.kiwipay.onboarding.keynua.domain.services;

import com.kiwipay.onboarding.keynua.dto.request.CreateContractPayloadRequest;
import com.kiwipay.onboarding.keynua.dto.request.CreateContractRequest;
import com.kiwipay.onboarding.keynua.dto.response.ContractCreationResponse;
import com.kiwipay.onboarding.keynua.dto.response.ContractPayloadResponse;

/**
 * Keynua Signing Service Interface
 * Handles contract payload generation and contract creation
 */
public interface KeynuaSigningService {

    /**
     * Generates Keynua contract payload for inspection/QA
     * 
     * @param request payload generation request
     * @return contract payload with missing fields list
     */
    ContractPayloadResponse generateContractPayload(CreateContractPayloadRequest request);

    /**
     * Creates a new contract in Keynua
     * Handles idempotency, validation, API call, and persistence
     * 
     * @param request contract creation request
     * @return contract creation response with signing information
     */
    ContractCreationResponse createContract(CreateContractRequest request);
}
