package com.kiwipay.onboarding.quote.domain.model.exceptions;

public class QuoteBusinessException extends RuntimeException {
    private final String errorCode;
    private final int httpStatus;

    public QuoteBusinessException(String message, String errorCode, int httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() { return errorCode; }
    public int getHttpStatus() { return httpStatus; }

    public static QuoteBusinessException clientNotFound() {
        return new QuoteBusinessException("Client not found for this quote", "CLIENT_NOT_FOUND", 404);
    }

    public static QuoteBusinessException quoteNotFound() {
        return new QuoteBusinessException("Quote not found", "QUOTE_NOT_FOUND", 404);
    }

    public static QuoteBusinessException invalidMonthlyIncome() {
        return new QuoteBusinessException("Monthly income must be greater than 0 with at most 2 decimal places", "INVALID_MONTHLY_INCOME", 422);
    }

    public static QuoteBusinessException invalidDocumentNumber() {
        return new QuoteBusinessException("Invalid document number for the given document type", "INVALID_DOCUMENT_NUMBER", 422);
    }

    public static QuoteBusinessException branchNotFound() {
        return new QuoteBusinessException("Branch not found", "BRANCH_NOT_FOUND", 404);
    }

    public static QuoteBusinessException quoteNotBelongsToClient() {
        return new QuoteBusinessException("Quote does not belong to the specified client", "QUOTE_NOT_BELONGS_TO_CLIENT", 403);
    }
}
