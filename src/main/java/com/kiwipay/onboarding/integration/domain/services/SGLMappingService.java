package com.kiwipay.onboarding.integration.domain.services;

import com.kiwipay.onboarding.client.application.internal.dto.ClientCreateRequest;
import com.kiwipay.onboarding.integration.application.internal.dto.SGLCotizacionDetalle;
import com.kiwipay.onboarding.integration.application.internal.dto.SGLLeadRequest;

/**
 * Domain service interface for mapping SGL data to Onboarding entities.
 * Defines the contract for data transformation between systems.
 */
public interface SGLMappingService {

    /**
     * Maps SGL lead request to Client creation request
     */
    ClientCreateRequest mapToClientCreateRequest(SGLLeadRequest sglLead);
    /**
     * Maps SGL lead request to Spouse creation request if spouse data exists
     */
    Object mapToSpouseCreateRequest(SGLLeadRequest sglLead);

    /**
     * Maps SGL lead request to ClinicalData creation request if clinical data exists
     */
    Object mapToClinicalDataCreateRequest(SGLLeadRequest sglLead);

    /**
     * Maps SGL cotization details to Risk creation request
     */
    Object mapToRiskCreateRequest(Long clientId, SGLCotizacionDetalle cotizacion);

    /**
     * Checks if SGL lead contains spouse data
     */
    boolean hasSpouseData(SGLLeadRequest sglLead);

    /**
     * Checks if SGL lead contains clinical data
     */
    boolean hasClinicalData(SGLLeadRequest sglLead);

    /**
     * Validates SGL lead data before processing
     */
    void validateSGLLeadData(SGLLeadRequest sglLead);
}