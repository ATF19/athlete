package com.atef.athlete.domain.model.core;

import java.time.Instant;

public record CreatedOn(Instant rawCreatedOn) {
    public static CreatedOn now() {
        return new CreatedOn(Instant.now());
    }
}
