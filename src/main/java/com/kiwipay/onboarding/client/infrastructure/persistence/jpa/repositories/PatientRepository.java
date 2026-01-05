package com.kiwipay.onboarding.client.infrastructure.persistence.jpa.repositories;

import com.kiwipay.onboarding.client.domain.model.aggregates.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Patient Repository
 * Manages persistence operations for Patient aggregate
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByLoanId(Long loanId);

    Optional<Patient> findByIdAndLoanId(Long id, Long loanId);

    void deleteByIdAndLoanId(Long id, Long loanId);

    boolean existsByIdAndLoanId(Long id, Long loanId);
}