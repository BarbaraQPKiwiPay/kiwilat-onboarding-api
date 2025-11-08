package com.kiwipay.onboarding.titular.domain.services;

import com.kiwipay.onboarding.titular.application.internal.dto.SpouseCreateRequest;
import com.kiwipay.onboarding.titular.application.internal.dto.SpousePatchRequest;
import com.kiwipay.onboarding.titular.application.internal.dto.SpouseResponse;
import com.kiwipay.onboarding.titular.application.internal.dto.SpouseUpdateRequest;

public interface SpouseCommandService {
    SpouseResponse createSpouse(Long clientId, SpouseCreateRequest request);
    SpouseResponse updateSpouse(Long clientId, SpouseUpdateRequest request);
    SpouseResponse patchSpouse(Long clientId, SpousePatchRequest request);
    void deleteSpouse(Long clientId);
}