package com.kiwipay.onboarding.titular.domain.model.exceptions;

public class ClinicalDataBusinessException extends RuntimeException {
    private final String errorCode;
    private final int httpStatus;
    
    public ClinicalDataBusinessException(String message, String errorCode, int httpStatus) {
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
    public static ClinicalDataBusinessException clinicalDataAlreadyExists() {
        return new ClinicalDataBusinessException(
            "Clinical data already exists for this client", 
            "CLINICAL_DATA_ALREADY_EXISTS", 
            409
        );
    }
    
    public static ClinicalDataBusinessException clinicalDataNotFound() {
        return new ClinicalDataBusinessException(
            "Clinical data not found for this client", 
            "CLINICAL_DATA_NOT_FOUND", 
            404
        );
    }
    
    public static ClinicalDataBusinessException invalidBranchForClinic() {
        return new ClinicalDataBusinessException(
            "The branch does not belong to the specified clinic", 
            "INVALID_BRANCH_FOR_CLINIC", 
            422
        );
    }
    
    public static ClinicalDataBusinessException invalidMonthlyIncome() {
        return new ClinicalDataBusinessException(
            "Monthly income must be greater than 0", 
            "INVALID_MONTHLY_INCOME", 
            400
        );
    }
}