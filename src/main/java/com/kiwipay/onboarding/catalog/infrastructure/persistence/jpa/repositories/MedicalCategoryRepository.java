package com.kiwipay.onboarding.catalog.infrastructure.persistence.jpa.repositories;

import com.kiwipay.onboarding.catalog.domain.model.entities.MedicalCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalCategoryRepository extends JpaRepository<MedicalCategory, String> {
}