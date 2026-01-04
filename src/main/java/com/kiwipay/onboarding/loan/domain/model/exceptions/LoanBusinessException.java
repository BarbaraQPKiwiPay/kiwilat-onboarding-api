package com.kiwipay.onboarding.loan.domain.model.exceptions;

import org.springframework.http.HttpStatus;

public class LoanBusinessException extends RuntimeException {

    private final String errorCode;
    private final HttpStatus httpStatus;

    public LoanBusinessException(String errorCode, String message, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public static LoanBusinessException loanNotFound() {
        return new LoanBusinessException(
                "LOAN_NOT_FOUND",
                "Loan not found with the provided ID",
                HttpStatus.NOT_FOUND);
    }

    public static LoanBusinessException clientNotFound() {
        return new LoanBusinessException(
                "CLIENT_NOT_FOUND",
                "Client not found with the provided ID",
                HttpStatus.NOT_FOUND);
    }

    public static LoanBusinessException clinicalDataNotFound() {
        return new LoanBusinessException(
                "CLINICAL_DATA_NOT_FOUND",
                "Clinical data not found with the provided ID",
                HttpStatus.NOT_FOUND);
    }

    public static LoanBusinessException invalidStatusTransition(String from, String to) {
        return new LoanBusinessException(
                "INVALID_STATUS_TRANSITION",
                String.format("Invalid status transition from %s to %s", from, to),
                HttpStatus.CONFLICT);
    }

    public static LoanBusinessException cannotDeleteLoan(String reason) {
        return new LoanBusinessException(
                "CANNOT_DELETE_LOAN",
                "Cannot delete loan: " + reason,
                HttpStatus.CONFLICT);
    }

    public static LoanBusinessException cannotUpdateLoan(String reason) {
        return new LoanBusinessException(
                "CANNOT_UPDATE_LOAN",
                "Cannot update loan: " + reason,
                HttpStatus.CONFLICT);
    }
}
