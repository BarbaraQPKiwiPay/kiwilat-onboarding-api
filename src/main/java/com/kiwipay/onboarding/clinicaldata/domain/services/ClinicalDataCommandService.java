package com.kiwipay.onboarding.titular.domain.services;

import com.kiwipay.onboarding.titular.application.internal.dto.ClinicalDataCreateRequest;
import com.kiwipay.onboarding.titular.application.internal.dto.ClinicalDataResponse;

public interface ClinicalDataCommandService {
    ClinicalDataResponse createClinicalData(Long clientId, ClinicalDataCreateRequest request);
    ClinicalDataResponse updateClinicalData(Long clientId, ClinicalDataCreateRequest request);
    void deleteClinicalData(Long clientId);
}