package com.kiwipay.onboarding.keynua.infrastructure.persistence;

import com.kiwipay.onboarding.keynua.domain.model.aggregates.KeynuaContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeynuaContractRepository extends JpaRepository<KeynuaContractEntity, Long> {

    /**
     * Find contract by Keynua contract ID
     */
    Optional<KeynuaContractEntity> findByContractId(String contractId);

    /**
     * Find active contract by loan ID and status list (for idempotency check)
     */
    Optional<KeynuaContractEntity> findByLoanIdAndStatusIn(String loanId, List<String> statuses);

    /**
     * Find all contracts for a loan ID
     */
    List<KeynuaContractEntity> findByLoanId(String loanId);
}
