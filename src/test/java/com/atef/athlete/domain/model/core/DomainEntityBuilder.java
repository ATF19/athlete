package com.atef.athlete.domain.model.core;

import com.atef.athlete.domain.model.user.Username;

import java.util.Optional;

public abstract class DomainEntityBuilder<T extends Id> {
    protected T id = defaultId();
    protected EntityStatus status = EntityStatus.ENABLED;
    protected CreatedOn createdOn = CreatedOn.now();
    protected CreatedBy createdBy = new CreatedBy(new Username("Builder"));
    protected Optional<UpdatedOn> updatedOn = Optional.empty();
    protected Optional<UpdatedBy> updatedBy = Optional.empty();

    protected abstract T defaultId();

    public DomainEntityBuilder<T> withId(T id) {
        this.id = id;
        return this;
    }

    public DomainEntityBuilder<T> withStatus(EntityStatus status) {
        this.status = status;
        return this;
    }

    public DomainEntityBuilder<T> withCreatedOn(CreatedOn createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public DomainEntityBuilder<T> withCreatedBy(CreatedBy createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public DomainEntityBuilder<T> withUpdatedOn(Optional<UpdatedOn> updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public DomainEntityBuilder<T> withUpdatedBy(Optional<UpdatedBy> updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }
}
