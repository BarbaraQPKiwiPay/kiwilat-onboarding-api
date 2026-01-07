package com.kiwipay.onboarding.catalog.infrastructure.persistence.jpa.repositories;

import com.kiwipay.onboarding.catalog.domain.model.entities.ClinicBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClinicBranch Repository
 * Handles persistence operations for ClinicBranch entity
 */
@Repository
public interface ClinicBranchRepository extends JpaRepository<ClinicBranch, String> {

       @Query("SELECT cb FROM ClinicBranch cb WHERE cb.clinicId = :clinicId")
       List<ClinicBranch> findByClinicId(@Param("clinicId") String clinicId);

       @Query("SELECT CASE WHEN COUNT(cb) > 0 THEN true ELSE false END " +
                     "FROM ClinicBranch cb WHERE cb.id = :branchId AND cb.clinicId = :clinicId")
       boolean existsByIdAndClinicId(@Param("branchId") String branchId,
                     @Param("clinicId") String clinicId);
}