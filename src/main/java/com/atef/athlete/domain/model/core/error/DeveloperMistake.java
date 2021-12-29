package com.atef.athlete.domain.model.core.error;

public class DeveloperMistake extends AthleteException {
    public DeveloperMistake() {
    }

    public DeveloperMistake(String message) {
        super(message);
    }

    public DeveloperMistake(String message, Throwable cause) {
        super(message, cause);
    }
}
