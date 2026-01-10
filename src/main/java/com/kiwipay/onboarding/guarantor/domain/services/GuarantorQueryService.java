package com.kiwipay.onboarding.guarantor.domain.services;

import com.kiwipay.onboarding.guarantor.application.internal.dto.GuarantorResponse;

import java.util.List;

public interface GuarantorQueryService {
    GuarantorResponse getGuarantorById(Long guarantorId);

    List<GuarantorResponse> getGuarantorsByLoanId(Long loanId);
}