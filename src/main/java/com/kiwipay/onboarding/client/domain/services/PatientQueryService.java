package com.kiwipay.onboarding.titular.domain.services;

import com.kiwipay.onboarding.titular.application.internal.dto.PatientResponse;
import com.kiwipay.onboarding.titular.application.internal.dto.PatientSummaryResponse;

import java.util.List;

public interface PatientQueryService {
    PatientResponse getPatientById(Long clientId, Long patientId);
    List<PatientSummaryResponse> getPatientsByClientId(Long clientId);
}