package com.kiwipay.onboarding.document.infrastructure.persistence.jpa;

import com.kiwipay.onboarding.document.domain.model.aggregates.Document;
import com.kiwipay.onboarding.document.domain.model.valueobjects.DocumentOwnerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, String> {

    List<Document> findByLoanId(Long loanId);

    List<Document> findByLoanIdAndOwnerType(Long loanId, DocumentOwnerType ownerType);

    List<Document> findByClientId(Long clientId);

    List<Document> findByGuarantorId(Long guarantorId);

    boolean existsByLoanIdAndId(Long loanId, String id);
}