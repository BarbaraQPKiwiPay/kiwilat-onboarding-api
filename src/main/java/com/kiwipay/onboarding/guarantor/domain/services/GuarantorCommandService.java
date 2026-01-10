package com.kiwipay.onboarding.guarantor.domain.services;

import com.kiwipay.onboarding.guarantor.application.internal.dto.GuarantorCreateRequest;
import com.kiwipay.onboarding.guarantor.application.internal.dto.GuarantorResponse;

import java.util.Map;

public interface GuarantorCommandService {
    GuarantorResponse createGuarantor(Long loanId, GuarantorCreateRequest request);

    GuarantorResponse updateGuarantor(Long guarantorId, GuarantorCreateRequest request);

    GuarantorResponse patchGuarantor(Long guarantorId, Map<String, Object> updates);

    void deleteGuarantor(Long guarantorId);
}