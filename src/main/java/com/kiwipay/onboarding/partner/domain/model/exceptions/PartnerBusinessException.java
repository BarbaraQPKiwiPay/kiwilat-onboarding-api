package com.kiwipay.onboarding.partner.domain.model.exceptions;

/**
 * Exception thrown when partner business rules are violated
 */
public class PartnerBusinessException extends RuntimeException {

    public PartnerBusinessException(String message) {
        super(message);
    }

    public PartnerBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
