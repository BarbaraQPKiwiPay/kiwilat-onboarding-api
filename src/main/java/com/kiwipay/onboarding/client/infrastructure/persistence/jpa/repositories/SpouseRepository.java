package com.kiwipay.onboarding.titular.infrastructure.persistence.jpa.repositories;

import com.kiwipay.onboarding.client.domain.model.aggregates.Spouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpouseRepository extends JpaRepository<Spouse, Long> {
    Optional<Spouse> findByClientId(Long clientId);
    boolean existsByClientId(Long clientId);
    boolean existsByDocumentNumber(String documentNumber);
    void deleteByClientId(Long clientId);
}