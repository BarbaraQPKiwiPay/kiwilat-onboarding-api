package com.kiwipay.onboarding.risk.infrastructure.persistence.jpa.repositories;

import com.kiwipay.onboarding.risk.infrastructure.persistence.jpa.entities.MedicalRiskInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalRiskInfoRepository extends JpaRepository<MedicalRiskInfoEntity, Long> {
    Optional<MedicalRiskInfoEntity> findByClientId(Long clientId);
    boolean existsByClientId(Long clientId);
}