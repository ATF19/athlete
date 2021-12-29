package com.atef.athlete.domain.model.core.error;

public class AthleteTechnicalException extends AthleteException {
    public AthleteTechnicalException() {
    }

    public AthleteTechnicalException(String message) {
        super(message);
    }

    public AthleteTechnicalException(String message, Throwable cause) {
        super(message, cause);
    }
}
