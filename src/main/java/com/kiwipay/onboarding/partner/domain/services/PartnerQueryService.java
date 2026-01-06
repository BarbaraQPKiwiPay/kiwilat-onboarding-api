package com.kiwipay.onboarding.partner.domain.services;

import com.kiwipay.onboarding.partner.application.internal.dto.PartnerResponse;

import java.util.List;
import java.util.Optional;

/**
 * Query Service for Spouse operations
 * Handles read-only operations
 */
public interface PartnerQueryService {

    /**
     * Get spouse by ID
     * 
     * @param id spouse ID
     * @return spouse response if found
     */
    Optional<PartnerResponse> getSpouseById(Long id);

    /**
     * Get all spouses for a given loan
     * 
     * @param loanId loan ID
     * @return list of spouse responses
     */
    List<PartnerResponse> getSpousesByLoan(Long loanId);

    /**
     * Get spouse by client ID
     * 
     * @param clientId client ID
     * @return spouse response if found
     */
    Optional<PartnerResponse> getSpouseByClient(Long clientId);

    /**
     * Get spouse by guarantor ID
     * 
     * @param guarantorId guarantor ID
     * @return spouse response if found
     */
    Optional<PartnerResponse> getSpouseByGuarantor(String guarantorId);

    /**
     * Get spouse by patient ID
     * 
     * @param patientId patient ID
     * @return spouse response if found
     */
    Optional<PartnerResponse> getSpouseByPatient(Long patientId);
}
