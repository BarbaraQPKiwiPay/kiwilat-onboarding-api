package com.kiwipay.onboarding.client.domain.model.exceptions;

public class PatientBusinessException extends RuntimeException {
    private final String errorCode;
    private final int httpStatus;

    public PatientBusinessException(String message, String errorCode, int httpStatus) {
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

    // Business rule exceptions
    public static PatientBusinessException patientNotFound() {
        return new PatientBusinessException("Patient not found", "PATIENT_NOT_FOUND", 404);
    }

    public static PatientBusinessException clientNotFound() {
        return new PatientBusinessException("Client not found", "CLIENT_NOT_FOUND", 404);
    }

    public static PatientBusinessException patientAlreadyExists() {
        return new PatientBusinessException("Patient already exists with this information", "PATIENT_ALREADY_EXISTS", 409);
    }

    public static PatientBusinessException invalidPatientData() {
        return new PatientBusinessException("Invalid patient data provided", "INVALID_PATIENT_DATA", 422);
    }

    public static PatientBusinessException patientNotBelongsToClient() {
        return new PatientBusinessException("Patient does not belong to the specified client", "PATIENT_NOT_BELONGS_TO_CLIENT", 403);
    }
}