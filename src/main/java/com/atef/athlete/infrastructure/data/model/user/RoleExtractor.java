package com.atef.athlete.infrastructure.data.model.user;

import com.atef.athlete.domain.model.core.error.DeveloperMistake;
import com.atef.athlete.domain.model.user.role.Role;
import com.atef.athlete.domain.model.user.role.SuperAdmin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class RoleExtractor {

    private static final RoleHandlers roleHandlers = RoleHandlers.builder()
            .with(SuperAdmin.class, payload -> new SuperAdmin())
            .build();

    public static Role extractRoleFromName(String rawRoleType, String rolePayload) {
        return roleHandlers.handle(rawRoleType, rolePayload);
    }
}

class RoleHandlers {
    List<RoleHandler<?>> handlers;

    RoleHandlers(List<RoleHandler<?>> handlers) {
        this.handlers = handlers;
    }

    static Builder builder() {
        return new Builder();
    }

    Role handle(String rawRoleType, String rolePayload) {
        return handlers.stream()
                .filter(h -> h.isHandlerFor(rawRoleType))
                .findFirst()
                .orElseThrow(() -> new DeveloperMistake("Role " + rawRoleType + " is unknown."))
                .handler()
                .apply(rolePayload);
    }

    static class Builder {
        List<RoleHandler<?>> handlers = new ArrayList<>();

        <T extends Role> Builder with(Class<T> type, Function<String, T> roleExtractor) {
            handlers.add(new RoleHandler<>(type, roleExtractor));
            return this;
        }

        RoleHandlers build() {
            return new RoleHandlers(handlers);
        }
    }
}

class RoleHandler<T extends Role> {
    Class<T> type;
    Function<String, T> roleExtractor;

    RoleHandler(Class<T> type, Function<String, T> roleExtractor) {
        this.type = type;
        this.roleExtractor = roleExtractor;
    }

    boolean isHandlerFor(String rawRoleType) {
        return Objects.equals(type.getName(), rawRoleType);
    }

    Function<String, T> handler() {
        return roleExtractor;
    }
}