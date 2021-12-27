package com.atef.athlete.domain.model.core;

import java.util.Objects;
import java.util.UUID;

public abstract class Id {
    public final UUID rawId;

    public Id() {
        this(UUID.randomUUID());
    }

    public Id(String rawId) {
        this(UUID.fromString(rawId));
    }

    public Id(UUID uuid) {
        rawId = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Id id = (Id) o;
        return Objects.equals(rawId, id.rawId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rawId);
    }

    @Override
    public String toString() {
        return "Id{" +
                "rawId=" + rawId +
                '}';
    }
}
