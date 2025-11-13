package com.kiwipay.onboarding.document.domain.model.exceptions;

public class DocumentBusinessException extends RuntimeException {
    private final String errorCode;
    private final int httpStatus;

    public DocumentBusinessException(String message, String errorCode, int httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() { return errorCode; }
    public int getHttpStatus() { return httpStatus; }

    public static DocumentBusinessException documentNotFound() {
        return new DocumentBusinessException("Document not found", "DOCUMENT_NOT_FOUND", 404);
    }

    public static DocumentBusinessException clientNotFound() {
        return new DocumentBusinessException("Client not found", "CLIENT_NOT_FOUND", 404);
    }

    public static DocumentBusinessException documentTypeNotFound() {
        return new DocumentBusinessException("Document type not found", "DOCUMENT_TYPE_NOT_FOUND", 404);
    }

    public static DocumentBusinessException invalidMimeType() {
        return new DocumentBusinessException("Invalid MIME type. Only PDF, JPG, and PNG are allowed", "INVALID_MIME_TYPE", 400);
    }

    public static DocumentBusinessException fileSizeExceeded() {
        return new DocumentBusinessException("File size exceeds maximum allowed limit", "FILE_SIZE_EXCEEDED", 413);
    }

    public static DocumentBusinessException maxDocumentsExceeded() {
        return new DocumentBusinessException("Maximum number of documents (10) exceeded for this client", "MAX_DOCUMENTS_EXCEEDED", 409);
    }

    public static DocumentBusinessException invalidBase64() {
        return new DocumentBusinessException("Invalid Base64 content", "INVALID_BASE64", 400);
    }

    public static DocumentBusinessException documentNotBelongsToClient() {
        return new DocumentBusinessException("Document does not belong to the specified client", "DOCUMENT_NOT_BELONGS_TO_CLIENT", 403);
    }
}