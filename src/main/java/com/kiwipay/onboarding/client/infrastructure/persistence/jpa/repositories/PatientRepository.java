package com.kiwipay.onboarding.titular.infrastructure.persistence.jpa.repositories;

import com.kiwipay.onboarding.client.domain.model.aggregates.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByClientId(Long clientId);
    Optional<Patient> findByIdAndClientId(Long id, Long clientId);
    void deleteByIdAndClientId(Long id, Long clientId);
    boolean existsByIdAndClientId(Long id, Long clientId);
}