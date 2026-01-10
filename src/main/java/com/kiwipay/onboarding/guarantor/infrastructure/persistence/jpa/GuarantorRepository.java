package com.kiwipay.onboarding.guarantor.infrastructure.persistence.jpa;

import com.kiwipay.onboarding.guarantor.domain.model.aggregates.Guarantor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuarantorRepository extends JpaRepository<Guarantor, Long> {
    List<Guarantor> findByLoanId(Long loanId);

    Optional<Guarantor> findByLoanIdAndId(Long loanId, Long id);

    boolean existsByLoanIdAndId(Long loanId, Long id);
}