package com.kiwipay.onboarding.client.domain.services;

import com.kiwipay.onboarding.client.application.internal.dto.SpouseResponse;

public interface SpouseQueryService {
    SpouseResponse getSpouseByClientId(Long clientId);
}