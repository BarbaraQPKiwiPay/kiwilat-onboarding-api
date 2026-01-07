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
     * Get partner by ID
     * 
     * @param id partner ID
     * @return partner response if found
     */
    Optional<PartnerResponse> getPartnerById(Long id);

    /**
     * Get all partners for a given loan
     * 
     * @param loanId loan ID
     * @return list of partner responses
     */
    List<PartnerResponse> getPartnersByLoan(Long loanId);

    /**
     * Get partner by client ID
     * 
     * @param clientId client ID
     * @return partner response if found
     */
    Optional<PartnerResponse> getPartnerByClient(Long clientId);

    /**
     * Get partner by guarantor ID
     * 
     * @param guarantorId guarantor ID
     * @return partner response if found
     */
    Optional<PartnerResponse> getPartnerByGuarantor(String guarantorId);

    /**
     * Get partner by patient ID
     * 
     * @param patientId patient ID
     * @return partner response if found
     */
    Optional<PartnerResponse> getPartnerByPatient(Long patientId);
}
