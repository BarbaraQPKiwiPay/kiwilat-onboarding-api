package com.kiwipay.onboarding.client.domain.services;

import com.kiwipay.onboarding.client.application.internal.dto.PatientResponse;
import com.kiwipay.onboarding.client.application.internal.dto.PatientSummaryResponse;

import java.util.List;

public interface PatientQueryService {
    PatientResponse getPatientById(Long clientId, Long patientId);
    List<PatientSummaryResponse> getPatientsByClientId(Long clientId);
    List<PatientResponse> getAllPatientsByClientId(Long clientId);
}