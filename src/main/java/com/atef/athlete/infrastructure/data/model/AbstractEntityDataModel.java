package com.atef.athlete.infrastructure.data.model;

import com.atef.athlete.domain.model.core.*;
import com.atef.athlete.domain.model.user.Username;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@MappedSuperclass
@Data
public abstract class AbstractEntityDataModel {
    @Id
    @Column(name = "ID")
    private UUID id;

    @Column(name = "STATUS", nullable = false)
    private EntityStatus status;

    @Column(name = "CREATED_ON", nullable = false, updatable = false)
    private Instant createdOn;

    @Column(name = "CREATED_BY", nullable = false, updatable = false)
    private String createdBy;

    @Column(name = "UPDATED_ON")
    private Instant updatedOn;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    public AbstractEntityDataModel() {
    }

    public AbstractEntityDataModel(UUID id, EntityStatus status, Instant createdOn, String createdBy, Instant updatedOn, String updatedBy) {
        this.id = id;
        this.status = status;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
    }

    public CreatedOn createdOn() {
        return new CreatedOn(createdOn);
    }

    public CreatedBy createdBy() {
        return new CreatedBy(new Username(createdBy));
    }

    public Optional<UpdatedOn> updatedOn() {
        return Optional.ofNullable(updatedOn).map(UpdatedOn::new);
    }

    public Optional<UpdatedBy> updatedBy() {
        return Optional.ofNullable(updatedBy).map(u -> new UpdatedBy(new Username(u)));
    }
}
