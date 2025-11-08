package com.kiwipay.onboarding.client.domain.services;

import com.kiwipay.onboarding.client.application.internal.dto.PatientCreateRequest;
import com.kiwipay.onboarding.client.application.internal.dto.PatientResponse;
import com.kiwipay.onboarding.client.application.internal.dto.PatientUpdateRequest;

public interface PatientCommandService {
    PatientResponse createPatient(Long clientId, PatientCreateRequest request);
    PatientResponse updatePatient(Long clientId, Long patientId, PatientUpdateRequest request);
    void deletePatient(Long clientId, Long patientId);
}