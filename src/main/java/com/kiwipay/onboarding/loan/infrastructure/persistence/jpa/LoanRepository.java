package com.kiwipay.onboarding.loan.infrastructure.persistence.jpa;

import com.kiwipay.onboarding.loan.domain.model.aggregates.Loan;
import com.kiwipay.onboarding.loan.domain.model.valueobjects.LoanStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository for Loan aggregate root
 * Uses ONLY Spring Data Query Methods (NO custom @Query)
 */
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

        // ========== BY CLIENT ==========
        /**
         * Find all loans for a specific client
         */
        List<Loan> findByClientId(Long clientId);

        /**
         * Find all loans for a client with pagination
         */
        Page<Loan> findByClientId(Long clientId, Pageable pageable);

        /**
         * Check if client has any loans
         */
        boolean existsByClientId(Long clientId);

        /**
         * Count loans for a client
         */
        long countByClientId(Long clientId);

        // ========== BY STATUS ==========
        /**
         * Find all loans with specific status
         */
        List<Loan> findByLoanStatus(LoanStatus loanStatus);

        /**
         * Find loans with status and pagination
         */
        Page<Loan> findByLoanStatus(LoanStatus loanStatus, Pageable pageable);

        /**
         * Find loans with status ordered by creation date
         */
        List<Loan> findByLoanStatusOrderByCreatedAtDesc(LoanStatus loanStatus);

        // ========== BY CLIENT AND STATUS ==========
        /**
         * Find loans for client with specific status
         */
        List<Loan> findByClientIdAndLoanStatus(Long clientId, LoanStatus loanStatus);

        /**
         * Find the most recent loan for client with specific status
         */
        Optional<Loan> findFirstByClientIdAndLoanStatusOrderByCreatedAtDesc(
                        Long clientId, LoanStatus loanStatus);

        // ========== BY DATE RANGE ==========
        /**
         * Find loans created between dates
         */
        List<Loan> findByCreatedAtBetween(
                        OffsetDateTime startDate, OffsetDateTime endDate);

        /**
         * Find loans created after a date
         */
        List<Loan> findByCreatedAtAfter(OffsetDateTime date);

        // ========== BY CLINICAL DATA ==========
        /**
         * Find loan by clinical data
         */
        Optional<Loan> findByClinicalDataId(Long clinicalDataId);

        /**
         * Check if clinical data is associated with a loan
         */
        boolean existsByClinicalDataId(Long clinicalDataId);

        // ========== COMPLEX QUERIES ==========
        /**
         * Find all loans for client ordered by creation date
         */
        List<Loan> findByClientIdOrderByCreatedAtDesc(Long clientId);

        /**
         * Find loans for client excluding certain statuses
         */
        List<Loan> findByClientIdAndLoanStatusNot(Long clientId, LoanStatus excludeStatus);

        /**
         * Find loans for client in multiple statuses
         */
        List<Loan> findByClientIdAndLoanStatusIn(Long clientId, List<LoanStatus> statuses);
}
