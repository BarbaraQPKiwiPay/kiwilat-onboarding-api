package com.kiwipay.onboarding.catalog.application.internal.queryservices;

import com.kiwipay.onboarding.catalog.application.internal.dto.ClinicBranchDto;
import com.kiwipay.onboarding.catalog.application.internal.dto.ClinicDto;
import com.kiwipay.onboarding.catalog.application.internal.dto.MedicalCategoryDto;
import com.kiwipay.onboarding.catalog.domain.model.aggregates.Clinic;
import com.kiwipay.onboarding.catalog.domain.model.entities.ClinicBranch;
import com.kiwipay.onboarding.catalog.domain.model.entities.MedicalCategory;
import com.kiwipay.onboarding.catalog.domain.services.CatalogQueryService;
import com.kiwipay.onboarding.catalog.infrastructure.persistence.jpa.repositories.ClinicBranchRepository;
import com.kiwipay.onboarding.catalog.infrastructure.persistence.jpa.repositories.ClinicRepository;
import com.kiwipay.onboarding.catalog.infrastructure.persistence.jpa.repositories.MedicalCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogQueryServiceImpl implements CatalogQueryService {
    
    @Autowired
    private MedicalCategoryRepository medicalCategoryRepository;
    
    @Autowired
    private ClinicRepository clinicRepository;
    
    @Autowired
    private ClinicBranchRepository clinicBranchRepository;

    @Override
    public List<MedicalCategoryDto> getAllMedicalCategories() {
        return medicalCategoryRepository.findAll().stream()
            .map(this::toMedicalCategoryDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<ClinicDto> getClinicsByCategory(String categoryId) {
        return clinicRepository.findByMedicalCategoryId(categoryId).stream()
            .map(this::toClinicDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<ClinicBranchDto> getBranchesByClinic(String clinicId) {
        return clinicBranchRepository.findByClinicId(clinicId).stream()
            .map(this::toClinicBranchDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<ClinicDto> searchClinics(String query, String categoryId) {
        return clinicRepository.searchByNameAndCategory(query, categoryId).stream()
            .map(this::toClinicDto)
            .collect(Collectors.toList());
    }

    // Mappers
    private MedicalCategoryDto toMedicalCategoryDto(MedicalCategory entity) {
        return new MedicalCategoryDto(entity.getId(), entity.getName());
    }

    private ClinicDto toClinicDto(Clinic entity) {
        return new ClinicDto(entity.getId(), entity.getName());
    }

    private ClinicBranchDto toClinicBranchDto(ClinicBranch entity) {
        return new ClinicBranchDto(entity.getId(), entity.getName());
    }
}