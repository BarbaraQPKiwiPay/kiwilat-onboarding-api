package com.kiwipay.onboarding.titular.domain.services;

import com.kiwipay.onboarding.titular.application.internal.dto.ClinicalDataResponse;

public interface ClinicalDataQueryService {
    ClinicalDataResponse getClinicalDataByClientId(Long clientId);
}