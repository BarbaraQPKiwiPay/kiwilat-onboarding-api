package com.kiwipay.onboarding.risk.domain.services;

import com.kiwipay.onboarding.risk.application.internal.dto.LoanRiskInfoResponse;
import com.kiwipay.onboarding.risk.application.internal.dto.MedicalRiskInfoResponse;
import com.kiwipay.onboarding.risk.application.internal.dto.PatientRiskInfoResponse;

public interface RiskQueryService {
    PatientRiskInfoResponse getPatientRiskInfo(Long clientId);
    LoanRiskInfoResponse getLoanRiskInfo(Long clientId);
    MedicalRiskInfoResponse getMedicalRiskInfo(Long clientId);
}