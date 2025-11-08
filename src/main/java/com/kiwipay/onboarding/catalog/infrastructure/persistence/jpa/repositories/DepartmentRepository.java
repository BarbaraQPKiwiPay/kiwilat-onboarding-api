package com.kiwipay.onboarding.catalog.infrastructure.persistence.jpa.repositories;

import com.kiwipay.onboarding.catalog.domain.model.aggregates.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
}
