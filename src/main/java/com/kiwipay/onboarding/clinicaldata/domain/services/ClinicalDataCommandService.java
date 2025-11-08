package com.kiwipay.onboarding.clinicaldata.domain.services;

import com.kiwipay.onboarding.clinicaldata.application.internal.dto.ClinicalDataCreateRequest;
import com.kiwipay.onboarding.clinicaldata.application.internal.dto.ClinicalDataResponse;

public interface ClinicalDataCommandService {
    ClinicalDataResponse createClinicalData(Long clientId, ClinicalDataCreateRequest request);
    ClinicalDataResponse updateClinicalData(Long clientId, ClinicalDataCreateRequest request);
    void deleteClinicalData(Long clientId);
}