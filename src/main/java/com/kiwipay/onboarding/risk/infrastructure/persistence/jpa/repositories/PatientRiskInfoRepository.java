package com.kiwipay.onboarding.risk.infrastructure.persistence.jpa.repositories;

import com.kiwipay.onboarding.risk.infrastructure.persistence.jpa.entities.PatientRiskInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRiskInfoRepository extends JpaRepository<PatientRiskInfoEntity, Long> {
    Optional<PatientRiskInfoEntity> findByClientId(Long clientId);
    boolean existsByClientId(Long clientId);
}