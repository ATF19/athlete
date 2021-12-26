package com.atef.athlete.domain.model.user.role;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Roles {
    private final Set<Role> grantedRoles;

    private Roles(Set<Role> grantedRoles) {
        this.grantedRoles = grantedRoles;
    }

    public static Roles empty() {
        return new Roles(new HashSet<>());
    }

    public static Roles from(Role... role) {
        return new Roles(Sets.newHashSet(role));
    }

    public static Roles from(Set<Role> roles) {
        return new Roles(Sets.newHashSet(roles));
    }

    public void grant(Role role) {
        grantedRoles.add(role);
    }

    public void revoke(Role role) {
        grantedRoles.remove(role);
    }

    public boolean have(Role role) {
        return grantedRoles.contains(role);
    }

    public Stream<Role> grantedRoles() {
        return grantedRoles.stream();
    }
}
