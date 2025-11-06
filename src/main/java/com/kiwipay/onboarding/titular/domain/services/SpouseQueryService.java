package com.kiwipay.onboarding.titular.domain.services;

import com.kiwipay.onboarding.titular.application.internal.dto.SpouseResponse;

public interface SpouseQueryService {
    SpouseResponse getSpouseByClientId(Long clientId);
}