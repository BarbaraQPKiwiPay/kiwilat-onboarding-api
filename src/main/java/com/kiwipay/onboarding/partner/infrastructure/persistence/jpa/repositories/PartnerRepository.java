package com.kiwipay.onboarding.partner.infrastructure.persistence.jpa.repositories;

import com.kiwipay.onboarding.partner.domain.model.aggregates.Partner;
import com.kiwipay.onboarding.partner.domain.model.valueobjects.PartnerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Spouse entity
 * Provides query methods for finding spouses by different criteria
 */
@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

    /**
     * Find all spouses for a given loan
     */
    List<Partner> findByLoanId(Long loanId);

    /**
     * Find spouse by client ID
     * Should return at most one record due to unique constraint
     */
    Optional<Partner> findByClientId(Long clientId);

    /**
     * Find spouse by guarantor ID
     * Should return at most one record due to unique constraint
     */
    Optional<Partner> findByGuarantorId(String guarantorId);

    /**
     * Find spouse by patient ID
     * Should return at most one record due to unique constraint
     */
    Optional<Partner> findByPatientId(Long patientId);

    /**
     * Find spouse by document number
     * Should return at most one record due to unique constraint
     */
    Optional<Partner> findByDocumentNumber(String documentNumber);

    /**
     * Find all spouses of a specific type
     */
    List<Partner> findBySpouseType(PartnerType spouseType);

    /**
     * Find spouse by loan and spouse type
     */
    Optional<Partner> findByLoanIdAndSpouseType(Long loanId, PartnerType spouseType);
}
