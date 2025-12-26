package com.kiwipay.onboarding.keynua.infrastructure.client;

/**
 * Custom exception for Keynua API errors
 */
public class KeynuaApiException extends RuntimeException {

    private final Integer statusCode;

    public KeynuaApiException(String message) {
        super(message);
        this.statusCode = null;
    }

    public KeynuaApiException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public KeynuaApiException(String message, Throwable cause) {
        super(message, cause);
        this.statusCode = null;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
}
