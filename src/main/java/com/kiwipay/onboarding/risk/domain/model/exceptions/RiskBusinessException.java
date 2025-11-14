package com.kiwipay.onboarding.risk.domain.model.exceptions;

public class RiskBusinessException extends RuntimeException {
    private final String errorCode;
    private final int httpStatus;

    public RiskBusinessException(String message, String errorCode, int httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    // Risk-specific exceptions
    public static RiskBusinessException patientRiskInfoNotFound() {
        return new RiskBusinessException("Patient risk information not found", "PATIENT_RISK_NOT_FOUND", 404);
    }

    public static RiskBusinessException loanRiskInfoNotFound() {
        return new RiskBusinessException("Loan risk information not found", "LOAN_RISK_NOT_FOUND", 404);
    }

    public static RiskBusinessException medicalRiskInfoNotFound() {
        return new RiskBusinessException("Medical risk information not found", "MEDICAL_RISK_NOT_FOUND", 404);
    }

    public static RiskBusinessException clientNotFound() {
        return new RiskBusinessException("Client not found in risk system", "CLIENT_NOT_FOUND", 404);
    }

    public static RiskBusinessException riskPlatformUnavailable() {
        return new RiskBusinessException("Risk platform temporarily unavailable", "RISK_PLATFORM_UNAVAILABLE", 503);
    }
}