package com.atef.athlete.domain.model.core;

import java.util.Objects;
import java.util.Optional;

public abstract class DomainEntity<T extends Id> {
    private final T id;
    private EntityStatus status = EntityStatus.ENABLED;
    private final CreatedOn createdOn;
    private final CreatedBy createdBy;
    private Optional<UpdatedOn> updatedOn = Optional.empty();
    private Optional<UpdatedBy> updatedBy = Optional.empty();

    protected DomainEntity(T id, CreatedOn createdOn, CreatedBy createdBy) {
        this.id = id;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
    }

    protected DomainEntity(T id, EntityStatus status,
                           CreatedOn createdOn, CreatedBy createdBy,
                           Optional<UpdatedOn> updatedOn, Optional<UpdatedBy> updatedBy) {
        this.id = id;
        this.status = status;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
    }

    public void enable(UpdatedOn updatedOn, UpdatedBy updatedBy) {
        status = EntityStatus.ENABLED;
        this.updatedOn = Optional.ofNullable(updatedOn);
        this.updatedBy = Optional.ofNullable(updatedBy);
    }

    public void disable(UpdatedOn updatedOn, UpdatedBy updatedBy) {
        status = EntityStatus.DISABLED;
        this.updatedOn = Optional.ofNullable(updatedOn);
        this.updatedBy = Optional.ofNullable(updatedBy);
    }

    public void delete(UpdatedOn updatedOn, UpdatedBy updatedBy) {
        status = EntityStatus.DELETED;
        this.updatedOn = Optional.ofNullable(updatedOn);
        this.updatedBy = Optional.ofNullable(updatedBy);
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

    public T id() {
        return id;
    }

    public EntityStatus status() {
        return status;
    }

    public CreatedOn createdOn() {
        return createdOn;
    }

    public CreatedBy createdBy() {
        return createdBy;
    }

    public Optional<UpdatedOn> updatedOn() {
        return updatedOn;
    }

    public Optional<UpdatedBy> updatedBy() {
        return updatedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainEntity<?> that = (DomainEntity<?>) o;
        return Objects.equals(id, that.id) && status == that.status && Objects.equals(createdOn, that.createdOn) && Objects.equals(createdBy, that.createdBy) && Objects.equals(updatedOn, that.updatedOn) && Objects.equals(updatedBy, that.updatedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, createdOn, createdBy, updatedOn, updatedBy);
    }

    @Override
    public String toString() {
        return "DomainEntity{" +
                "id=" + id +
                ", status=" + status +
                ", createdOn=" + createdOn +
                ", createdBy=" + createdBy +
                ", updatedOn=" + updatedOn +
                ", updatedBy=" + updatedBy +
                '}';
    }
}

