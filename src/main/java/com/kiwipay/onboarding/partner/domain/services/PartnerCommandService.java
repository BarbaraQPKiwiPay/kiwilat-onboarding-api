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
     * Creates a new spouse
     * Validates that the owner entity (client/guarantor/patient) exists
     * Validates that no spouse already exists for the owner
     * 
     * @param request creation request with spouse data
     * @return the created spouse response
     * @throws com.kiwipay.onboarding.partner.domain.model.exceptions.PartnerBusinessException if
     *                                                                                         validation
     *                                                                                         fails
     */
    PartnerResponse createSpouse(CreatePartnerRequest request);

    /**
     * Updates an existing spouse
     * Cannot change spouseType or foreign keys
     * Only updates personal information
     * 
     * @param id      spouse ID to update
     * @param request update request with new data
     * @return the updated spouse response
     * @throws com.kiwipay.onboarding.partner.domain.model.exceptions.PartnerBusinessException if
     *                                                                                         spouse
     *                                                                                         not
     *                                                                                         found
     */
    PartnerResponse updateSpouse(Long id, UpdatePartnerRequest request);

    /**
     * Deletes a spouse
     * 
     * @param id spouse ID to delete
     * @throws com.kiwipay.onboarding.partner.domain.model.exceptions.PartnerBusinessException if
     *                                                                                         spouse
     *                                                                                         not
     *                                                                                         found
     */
    void deleteSpouse(Long id);
}
