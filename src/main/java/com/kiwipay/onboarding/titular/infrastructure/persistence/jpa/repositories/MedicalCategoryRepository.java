package com.kiwipay.onboarding.titular.infrastructure.persistence.jpa.repositories;

import com.kiwipay.onboarding.titular.domain.model.entities.MedicalCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalCategoryRepository extends JpaRepository<MedicalCategory, String> {
}