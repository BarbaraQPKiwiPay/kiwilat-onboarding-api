package com.kiwipay.onboarding.integration.domain.model.exceptions;

public class IntegrationBusinessException extends RuntimeException {
    private final String errorCode;
    private final int httpStatus;
    
    public IntegrationBusinessException(String message, String errorCode, int httpStatus) {
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
    public static IntegrationBusinessException leadAlreadyExists() {
        return new IntegrationBusinessException(
            "Lead already exists with this document number", 
            "LEAD_ALREADY_EXISTS", 
            409
        );
    }
    
    public static IntegrationBusinessException leadNotFound() {
        return new IntegrationBusinessException(
            "Lead not found", 
            "LEAD_NOT_FOUND", 
            404
        );
    }
    
    public static IntegrationBusinessException invalidQuotationData() {
        return new IntegrationBusinessException(
            "Invalid quotation data provided", 
            "INVALID_QUOTATION_DATA", 
            422
        );
    }
    
    public static IntegrationBusinessException invalidDocumentType() {
        return new IntegrationBusinessException(
            "Invalid document type provided", 
            "INVALID_DOCUMENT_TYPE", 
            422
        );
    }
    
    public static IntegrationBusinessException missingRequiredFields() {
        return new IntegrationBusinessException(
            "Missing required fields in lead data", 
            "MISSING_REQUIRED_FIELDS", 
            400
        );
    }
    
}