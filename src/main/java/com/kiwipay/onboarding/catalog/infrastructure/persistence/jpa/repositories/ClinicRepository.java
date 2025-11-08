package com.kiwipay.onboarding.catalog.infrastructure.persistence.jpa.repositories;

import com.kiwipay.onboarding.catalog.domain.model.aggregates.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, String> {
    
    @Query("SELECT c FROM Clinic c WHERE c.medicalCategory.id = :categoryId")
    List<Clinic> findByMedicalCategoryId(@Param("categoryId") String categoryId);
    
    @Query("SELECT c FROM Clinic c WHERE " +
           "(:categoryId IS NULL OR c.medicalCategory.id = :categoryId) AND " +
           "LOWER(c.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Clinic> searchByNameAndCategory(@Param("query") String query, 
                                        @Param("categoryId") String categoryId);
}