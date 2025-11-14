package com.kiwipay.onboarding.guarantor.infrastructure.persistence.jpa;

import com.kiwipay.onboarding.guarantor.domain.model.aggregates.Guarantor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuarantorRepository extends JpaRepository<Guarantor, String> {
    Optional<Guarantor> findByClientId(Long clientId);
    boolean existsByClientId(Long clientId);
    void deleteByClientId(Long clientId);
}