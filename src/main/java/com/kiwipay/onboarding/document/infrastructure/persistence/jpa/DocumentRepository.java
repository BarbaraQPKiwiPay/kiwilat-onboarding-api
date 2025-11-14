package com.kiwipay.onboarding.document.infrastructure.persistence.jpa;

import com.kiwipay.onboarding.document.domain.model.aggregates.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, String> {
    List<Document> findByClientIdOrderByCreatedAtDesc(Long clientId);
    Optional<Document> findByIdAndClientId(String id, Long clientId);
    long countByClientId(Long clientId);
    boolean existsByIdAndClientId(String id, Long clientId);
    
    // New methods for filtering by document type
    List<Document> findByClientIdAndDocumentTypeIdNotOrderByCreatedAtDesc(Long clientId, String documentTypeId);
    List<Document> findByClientIdAndDocumentTypeIdOrderByCreatedAtDesc(Long clientId, String documentTypeId);
}