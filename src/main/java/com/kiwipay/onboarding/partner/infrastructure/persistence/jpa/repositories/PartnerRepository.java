package com.kiwipay.onboarding.partner.infrastructure.persistence.jpa.repositories;

import com.kiwipay.onboarding.partner.domain.model.aggregates.Partner;
import com.kiwipay.onboarding.partner.domain.model.valueobjects.PartnerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Partner entity
 * Provides query methods for finding partners by different criteria
 */
@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

    /**
     * Find all partners for a given loan
     */
    List<Partner> findByLoanId(Long loanId);

    /**
     * Find partner by client ID
     * Should return at most one record due to unique constraint
     */
    Optional<Partner> findByClientId(Long clientId);

    /**
     * Find partner by guarantor ID
     * Should return at most one record due to unique constraint
     */
    Optional<Partner> findByGuarantorId(Long guarantorId);

    /**
     * Find partner by patient ID
     * Should return at most one record due to unique constraint
     */
    Optional<Partner> findByPatientId(Long patientId);

    /**
     * Find partner by document number
     * Should return at most one record due to unique constraint
     */
    Optional<Partner> findByDocumentNumber(String documentNumber);

    /**
     * Find all partners of a specific type
     */
    List<Partner> findByPartnerType(PartnerType partnerType);

    /**
     * Find partner by loan and partner type
     */
    Optional<Partner> findByLoanIdAndPartnerType(Long loanId, PartnerType partnerType);
}
