package com.example.consentapi.domain;

public class InvalidConsentException extends RuntimeException {

    public InvalidConsentException(String message) {
        super(message);
    }

    public InvalidConsentException(String message, Throwable cause) {
        super(message, cause);
    }

}
