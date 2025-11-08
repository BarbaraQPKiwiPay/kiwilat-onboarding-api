
package com.kiwipay.onboarding.client.domain.model.exceptions;

public class ClientBusinessException extends RuntimeException {
    private final String errorCode;
    private final int httpStatus;

    public ClientBusinessException(String message, String errorCode, int httpStatus) {
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
    public static ClientBusinessException invalidGender() {
        return new ClientBusinessException("Invalid gender. Must be M, F or OTHER", "INVALID_GENDER", 422);
    }

    public static ClientBusinessException clientNotFound() {
        return new ClientBusinessException("Client not found", "CLIENT_NOT_FOUND", 404);
    }
    public static ClientBusinessException documentAlreadyExists() {
        return new ClientBusinessException("Document number already exists", "DOCUMENT_ALREADY_EXISTS", 409);
    }
    public static ClientBusinessException emailAlreadyExists() {
        return new ClientBusinessException("Email already exists", "EMAIL_ALREADY_EXISTS", 409);
    }
    public static ClientBusinessException invalidDocumentType() {
        return new ClientBusinessException("Invalid document type", "INVALID_DOCUMENT_TYPE", 422);
    }
    public static ClientBusinessException invalidUbigeo() {
        return new ClientBusinessException("Invalid ubigeo (department, province, or district)", "INVALID_UBIGEO", 422);
    }
}
