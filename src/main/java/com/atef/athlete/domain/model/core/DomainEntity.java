package com.atef.athlete.domain.model.core;

import java.util.Objects;

public abstract class DomainEntity<T extends Id> {
    public final T id;
    private EntityStatus status = EntityStatus.ENABLED;

    public DomainEntity(T id) {
        this.id = id;
    }

    public DomainEntity(T id, EntityStatus status) {
        this.id = id;
        this.status = status;
    }

    public void enable() {
        status = EntityStatus.ENABLED;
    }

    public void disable() {
        status = EntityStatus.DISABLED;
    }

    public void delete() {
        status = EntityStatus.DELETED;
    }

    public boolean enabled() {
        return status == EntityStatus.ENABLED;
    }

    public boolean disabled() {
        return status == EntityStatus.DISABLED;
    }

    public boolean deleted() {
        return status == EntityStatus.DELETED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainEntity<?> that = (DomainEntity<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DomainEntity{" +
                "id=" + id +
                '}';
    }
}

