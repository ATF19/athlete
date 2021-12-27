package com.atef.athlete.domain.model.core;

import java.time.Instant;

public record UpdatedOn(Instant rawUpdatedOn) {
    public static UpdatedOn now() {
        return new UpdatedOn(Instant.now());
    }
}
