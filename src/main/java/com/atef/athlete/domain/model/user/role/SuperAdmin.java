package com.atef.athlete.domain.model.user.role;

public class SuperAdmin implements Role {

    @Override
    public RoleName roleName() {
        return new RoleName("SuperAdmin");
    }

    @Override
    public int hashCode() {
        return roleName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SuperAdmin;
    }
}
