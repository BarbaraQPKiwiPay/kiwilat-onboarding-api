package com.kiwipay.onboarding.partner.domain.services;

import com.kiwipay.onboarding.partner.application.internal.dto.CreatePartnerRequest;
import com.kiwipay.onboarding.partner.application.internal.dto.PartnerResponse;
import com.kiwipay.onboarding.partner.application.internal.dto.UpdatePartnerRequest;

/**
 * Command Service for Spouse operations
 * Handles create, update, and delete operations
 */
public interface PartnerCommandService {

    /**
     * Creates a new Partner
     * Validates that the owner entity (client/guarantor/patient) exists
     * Validates that no partner already exists for the owner
     * 
     * @param request creation request with partner data
     * @return the created partner response
     * @throws com.kiwipay.onboarding.partner.domain.model.exceptions.PartnerBusinessException if
     *                                                                                         validation
     *                                                                                         fails
     */
    PartnerResponse createPartner(CreatePartnerRequest request);

    /**
     * Updates an existing partner
     * Cannot change partnerType or foreign keys
     * Only updates personal information
     * 
     * @param id      partner ID to update
     * @param request update request with new data
     * @return the updated partner response
     * @throws com.kiwipay.onboarding.partner.domain.model.exceptions.PartnerBusinessException if
     *                                                                                         partner
     *                                                                                         not
     *                                                                                         found
     */
    PartnerResponse updatePartner(Long id, UpdatePartnerRequest request);

    /**
     * Deletes a partner
     * 
     * @param id partner ID to delete
     * @throws com.kiwipay.onboarding.partner.domain.model.exceptions.PartnerBusinessException if
     *                                                                                         partner
     *                                                                                         not
     *                                                                                         found
     */
    void deletePartner(Long id);
}
