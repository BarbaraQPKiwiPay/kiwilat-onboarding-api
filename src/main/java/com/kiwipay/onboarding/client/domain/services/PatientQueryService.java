package com.kiwipay.onboarding.client.domain.services;

import com.kiwipay.onboarding.client.application.internal.dto.PatientResponse;
import com.kiwipay.onboarding.client.application.internal.dto.PatientSummaryResponse;

import java.util.List;

/**
 * Patient Query Service
 * Handles read operations for Patient aggregate
 */
public interface PatientQueryService {
    PatientResponse getPatientById(Long loanId, Long patientId);

    List<PatientSummaryResponse> getPatientsByLoanId(Long loanId);

    List<PatientResponse> getAllPatientsByLoanId(Long loanId);
}