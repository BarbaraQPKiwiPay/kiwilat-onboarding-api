package com.kiwipay.onboarding.guarantor.infrastructure.persistence.jpa;

import com.kiwipay.onboarding.guarantor.domain.model.aggregates.GuarantorDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GuarantorDocumentRepository extends JpaRepository<GuarantorDocument, String> {
    List<GuarantorDocument> findByClientIdOrderByUploadedAtDesc(Long clientId);
    Optional<GuarantorDocument> findByIdAndClientId(String id, Long clientId);
    long countByClientId(Long clientId);
    boolean existsByIdAndClientId(String id, Long clientId);
}