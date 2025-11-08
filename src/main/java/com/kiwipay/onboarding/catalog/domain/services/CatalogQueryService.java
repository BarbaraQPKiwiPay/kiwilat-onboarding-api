package com.kiwipay.onboarding.catalog.domain.services;

import com.kiwipay.onboarding.catalog.application.internal.dto.ClinicBranchDto;
import com.kiwipay.onboarding.catalog.application.internal.dto.ClinicDto;
import com.kiwipay.onboarding.catalog.application.internal.dto.MedicalCategoryDto;

import java.util.List;

public interface CatalogQueryService {
    List<MedicalCategoryDto> getAllMedicalCategories();
    List<ClinicDto> getClinicsByCategory(String categoryId);
    List<ClinicBranchDto> getBranchesByClinic(String clinicId);
    List<ClinicDto> searchClinics(String query, String categoryId);
}