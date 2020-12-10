package com.example.barsa.exceptions;

public class BusinessValidationException extends Exception {
    private static final long serialVersionUID = 5849661867918702203L;

    public BusinessValidationException() {
    }

    public BusinessValidationException(String message) {
        super(message);
    }

    public BusinessValidationException(Throwable cause) {
        super(cause);
    }

    public BusinessValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
