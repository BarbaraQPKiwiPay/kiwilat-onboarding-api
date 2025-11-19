package com.kiwipay.onboarding.integration.domain.services;

import com.kiwipay.onboarding.integration.application.internal.dto.SGLIntegrationResponse;
import com.kiwipay.onboarding.integration.application.internal.dto.SGLLeadRequest;

/**
 * Domain service interface for processing SGL leads.
 * Orchestrates the integration workflow.
 */
public interface SGLIntegrationService {

    /**
     * Processes a complete SGL lead request
     * Creates client, spouse, clinical data, and risk data as needed
     */
    SGLIntegrationResponse processLead(SGLLeadRequest sglLead);
}