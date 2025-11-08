package com.kiwipay.onboarding.catalog.infrastructure.persistence.jpa.repositories;

import com.kiwipay.onboarding.catalog.domain.model.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, String> {
}
