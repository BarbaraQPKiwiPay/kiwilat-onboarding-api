package com.kiwipay.onboarding.document.infrastructure.persistence.jpa;

import com.kiwipay.onboarding.document.domain.model.entities.DocumentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentTypeEntity, String> {
}