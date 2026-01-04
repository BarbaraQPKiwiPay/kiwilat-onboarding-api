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

        List<Loan> findByClientId(Long clientId);

        Page<Loan> findByClientId(Long clientId, Pageable pageable);

        boolean existsByClientId(Long clientId);

        long countByClientId(Long clientId);

        List<Loan> findByLoanStatus(LoanStatus loanStatus);

        Page<Loan> findByLoanStatus(LoanStatus loanStatus, Pageable pageable);

        /**
         * Find loans with status ordered by creation date
         */
        List<Loan> findByLoanStatusOrderByCreatedAtDesc(LoanStatus loanStatus);

        /**
         * Find loans for client with specific status
         */
        List<Loan> findByClientIdAndLoanStatus(Long clientId, LoanStatus loanStatus);

        /**
         * Find the most recent loan for client with specific status
         */
        Optional<Loan> findFirstByClientIdAndLoanStatusOrderByCreatedAtDesc(Long clientId, LoanStatus loanStatus);

        List<Loan> findByCreatedAtBetween(OffsetDateTime startDate, OffsetDateTime endDate);

        List<Loan> findByCreatedAtAfter(OffsetDateTime date);

        Optional<Loan> findByClinicalDataId(Long clinicalDataId);

        boolean existsByClinicalDataId(Long clinicalDataId);

        List<Loan> findByClientIdOrderByCreatedAtDesc(Long clientId);

        List<Loan> findByClientIdAndLoanStatusNot(Long clientId, LoanStatus excludeStatus);

        List<Loan> findByClientIdAndLoanStatusIn(Long clientId, List<LoanStatus> statuses);
}
