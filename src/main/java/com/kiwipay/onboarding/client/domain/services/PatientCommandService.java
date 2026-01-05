package com.kiwipay.onboarding.client.domain.services;

import com.kiwipay.onboarding.client.application.internal.dto.PatientCreateRequest;
import com.kiwipay.onboarding.client.application.internal.dto.PatientResponse;
import com.kiwipay.onboarding.client.application.internal.dto.PatientUpdateRequest;

/**
 * Patient Command Service
 * Handles write operations for Patient aggregate
 */
public interface PatientCommandService {
    PatientResponse createPatient(Long loanId, PatientCreateRequest request);

    PatientResponse updatePatient(Long loanId, Long patientId, PatientUpdateRequest request);

    void deletePatient(Long loanId, Long patientId);
}