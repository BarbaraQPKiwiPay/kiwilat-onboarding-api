package com.kiwipay.onboarding.titular.domain.services;

import com.kiwipay.onboarding.titular.application.internal.dto.ClinicBranchDto;
import com.kiwipay.onboarding.titular.application.internal.dto.ClinicDto;
import com.kiwipay.onboarding.titular.application.internal.dto.MedicalCategoryDto;

import java.util.List;

public interface CatalogQueryService {
    List<MedicalCategoryDto> getAllMedicalCategories();
    List<ClinicDto> getClinicsByCategory(String categoryId);
    List<ClinicBranchDto> getBranchesByClinic(String clinicId);
    List<ClinicDto> searchClinics(String query, String categoryId);
}