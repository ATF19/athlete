package com.atef.athlete.domain.model.user;

import com.atef.athlete.domain.model.core.Id;

import java.util.UUID;

public class UserId extends Id {
    public UserId() {
    }

    public UserId(String rawId) {
        super(rawId);
    }

    public UserId(UUID uuid) {
        super(uuid);
    }
}
