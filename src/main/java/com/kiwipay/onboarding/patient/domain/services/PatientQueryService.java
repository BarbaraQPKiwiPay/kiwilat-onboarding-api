package com.kiwipay.onboarding.patient.domain.services;

import java.util.List;

import com.kiwipay.onboarding.patient.application.internal.dto.PatientResponse;
import com.kiwipay.onboarding.patient.application.internal.dto.PatientSummaryResponse;

/**
 * Patient Query Service
 * Handles read operations for Patient aggregate
 */
public interface PatientQueryService {
    PatientResponse getPatientById(Long loanId, Long patientId);

    List<PatientSummaryResponse> getPatientsByLoanId(Long loanId);

    List<PatientResponse> getAllPatientsByLoanId(Long loanId);
}