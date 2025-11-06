package com.kiwipay.onboarding.titular.infrastructure.persistence.jpa.repositories;

import com.kiwipay.onboarding.titular.domain.model.entities.ClinicBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicBranchRepository extends JpaRepository<ClinicBranch, String> {
    
    @Query("SELECT cb FROM ClinicBranch cb WHERE cb.clinic.id = :clinicId")
    List<ClinicBranch> findByClinicId(@Param("clinicId") String clinicId);
    
    @Query("SELECT CASE WHEN COUNT(cb) > 0 THEN true ELSE false END " +
           "FROM ClinicBranch cb WHERE cb.id = :branchId AND cb.clinic.id = :clinicId")
    boolean existsByIdAndClinicId(@Param("branchId") String branchId, 
                                 @Param("clinicId") String clinicId);
}