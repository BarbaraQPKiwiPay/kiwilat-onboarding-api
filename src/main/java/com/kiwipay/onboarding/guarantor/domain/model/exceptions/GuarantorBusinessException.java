package com.kiwipay.onboarding.guarantor.domain.model.exceptions;

public class GuarantorBusinessException extends RuntimeException {
    private final String errorCode;
    private final int httpStatus;

    public GuarantorBusinessException(String message, String errorCode, int httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() { return errorCode; }
    public int getHttpStatus() { return httpStatus; }

    // Guarantor exceptions
    public static GuarantorBusinessException guarantorNotFound() {
        return new GuarantorBusinessException("Guarantor not found", "GUARANTOR_NOT_FOUND", 404);
    }

    public static GuarantorBusinessException clientNotFound() {
        return new GuarantorBusinessException("Client not found", "CLIENT_NOT_FOUND", 404);
    }

    public static GuarantorBusinessException documentTypeNotFound() {
        return new GuarantorBusinessException("Document type not found", "DOCUMENT_TYPE_NOT_FOUND", 404);
    }

    // GuarantorDocument exceptions
    public static GuarantorBusinessException guarantorDocumentNotFound() {
        return new GuarantorBusinessException("Guarantor document not found", "GUARANTOR_DOCUMENT_NOT_FOUND", 404);
    }

    public static GuarantorBusinessException invalidMimeType() {
        return new GuarantorBusinessException("Invalid MIME type. Only PDF, JPG, and PNG are allowed", "INVALID_MIME_TYPE", 400);
    }

    public static GuarantorBusinessException fileSizeExceeded() {
        return new GuarantorBusinessException("File size exceeds maximum allowed limit", "FILE_SIZE_EXCEEDED", 413);
    }

    public static GuarantorBusinessException maxDocumentsExceeded() {
        return new GuarantorBusinessException("Maximum number of documents (10) exceeded for this guarantor", "MAX_DOCUMENTS_EXCEEDED", 409);
    }

    public static GuarantorBusinessException invalidBase64() {
        return new GuarantorBusinessException("Invalid Base64 content", "INVALID_BASE64", 400);
    }

    public static GuarantorBusinessException documentNotBelongsToClient() {
        return new GuarantorBusinessException("Document does not belong to the specified client", "DOCUMENT_NOT_BELONGS_TO_CLIENT", 403);
    }

    public static GuarantorBusinessException invalidReviewStatus() {
        return new GuarantorBusinessException("Invalid review status", "INVALID_REVIEW_STATUS", 400);
    }
}