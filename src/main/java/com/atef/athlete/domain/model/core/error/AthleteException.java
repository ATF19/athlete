package com.atef.athlete.domain.model.core.error;

public class AthleteException extends RuntimeException {
    public AthleteException() {
    }

    public AthleteException(String message) {
        super(message);
    }

    public AthleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
