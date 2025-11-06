package com.kiwipay.onboarding.titular.domain.model.exceptions;

public class SpouseBusinessException extends RuntimeException {
    private final String errorCode;
    private final int httpStatus;
    
    public SpouseBusinessException(String message, String errorCode, int httpStatus) {
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
    public static SpouseBusinessException spouseAlreadyExists() {
        return new SpouseBusinessException("Spouse already exists for this client", "SPOUSE_ALREADY_EXISTS", 409);
    }
    
    public static SpouseBusinessException clientNotMarried() {
        return new SpouseBusinessException("Client marital status is not compatible with having a spouse", "CLIENT_NOT_MARRIED", 422);
    }
    
    public static SpouseBusinessException documentAlreadyLinked() {
        return new SpouseBusinessException("Document number is already linked to another spouse", "DOCUMENT_ALREADY_LINKED", 409);
    }
    
    public static SpouseBusinessException spouseNotFound() {
        return new SpouseBusinessException("Spouse not found for this client", "SPOUSE_NOT_FOUND", 404);
    }
}