package com.kiwipay.onboarding.clinicaldata.infrastructure.persistence.jpa.repositories;

import com.kiwipay.onboarding.clinicaldata.domain.model.aggregates.ClinicalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Long> {
    Optional<ClinicalData> findByClientId(Long clientId);
    boolean existsByClientId(Long clientId);
    void deleteByClientId(Long clientId);
}