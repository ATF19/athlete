package com.atef.athlete.domain.model.core.error;

import com.atef.athlete.domain.model.core.DomainEntity;
import com.atef.athlete.domain.model.core.Id;

public class EntityNotFoundException extends AthleteBusinessException {
    private EntityNotFoundException(String message) {
        super(message);
    }

    public static <I extends Id, T extends DomainEntity<I>> EntityNotFoundException from(I id, Class<T> domainEntityType) {
        return new EntityNotFoundException("No element was found with type {" + domainEntityType.getSimpleName() +
                "} and Id {" + id.rawId.toString() + "}");
    }
}
