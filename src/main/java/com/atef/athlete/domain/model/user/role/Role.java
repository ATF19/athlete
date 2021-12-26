package com.atef.athlete.domain.model.user.role;

public interface Role {
    RoleName roleName();
}

record RoleName(String rawName) {
}