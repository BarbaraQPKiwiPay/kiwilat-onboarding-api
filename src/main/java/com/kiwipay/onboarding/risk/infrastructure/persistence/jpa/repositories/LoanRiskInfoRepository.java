package com.kiwipay.onboarding.risk.infrastructure.persistence.jpa.repositories;

import com.kiwipay.onboarding.risk.infrastructure.persistence.jpa.entities.LoanRiskInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRiskInfoRepository extends JpaRepository<LoanRiskInfoEntity, Long> {
    Optional<LoanRiskInfoEntity> findByClientId(Long clientId);
    boolean existsByClientId(Long clientId);
}