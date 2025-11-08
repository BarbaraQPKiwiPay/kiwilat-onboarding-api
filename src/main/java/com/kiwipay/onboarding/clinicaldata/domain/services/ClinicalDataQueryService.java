package com.kiwipay.onboarding.clinicaldata.domain.services;

import com.kiwipay.onboarding.clinicaldata.application.internal.dto.ClinicalDataResponse;

public interface ClinicalDataQueryService {
    ClinicalDataResponse getClinicalDataByClientId(Long clientId);
}