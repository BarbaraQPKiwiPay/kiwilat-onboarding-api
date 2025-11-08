package com.kiwipay.onboarding.clinicaldata.application.internal.queryservices;

import com.kiwipay.onboarding.clinicaldata.application.internal.dto.ClinicalDataResponse;
import com.kiwipay.onboarding.clinicaldata.domain.model.aggregates.ClinicalData;
import com.kiwipay.onboarding.clinicaldata.domain.services.ClinicalDataQueryService;
import com.kiwipay.onboarding.clinicaldata.infrastructure.persistence.jpa.repositories.ClinicalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicalDataQueryServiceImpl implements ClinicalDataQueryService {

    @Autowired
    private ClinicalDataRepository clinicalDataRepository;

    @Override
    public ClinicalDataResponse getClinicalDataByClientId(Long clientId) {
        return clinicalDataRepository.findByClientId(clientId)
            .map(this::toClinicalDataResponse)
            .orElse(null);
    }

    private ClinicalDataResponse toClinicalDataResponse(ClinicalData clinicalData) {
        ClinicalDataResponse response = new ClinicalDataResponse();
        response.setId("CD-" + clinicalData.getId());
        response.setClientId(clinicalData.getClientId());
        response.setMedicalCategoryId(clinicalData.getMedicalCategoryId());
        response.setClinicId(clinicalData.getClinicId());
        response.setBranchId(clinicalData.getBranchId());
        response.setCreatedAt(clinicalData.getCreatedAt().toString());
        response.setUpdatedAt(clinicalData.getUpdatedAt() != null ? 
                            clinicalData.getUpdatedAt().toString() : null);
        return response;
    }
}