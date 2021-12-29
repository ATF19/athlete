package com.atef.athlete.domain.model.core.error;

public class AthleteBusinessException extends AthleteException {
    public AthleteBusinessException() {
    }

    public AthleteBusinessException(String message) {
        super(message);
    }

    public AthleteBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
