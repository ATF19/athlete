package com.atef.athlete.infrastructure.data.repository.user;

import com.atef.athlete.domain.model.core.error.EntityNotFoundException;
import com.atef.athlete.domain.model.user.RegisteredUser;
import com.atef.athlete.domain.model.user.UserId;
import com.atef.athlete.domain.model.user.role.Role;
import com.atef.athlete.domain.service.user.Users;
import com.atef.athlete.infrastructure.data.model.user.RegisteredUserDataModel;
import com.atef.athlete.infrastructure.data.model.user.RoleDataModel;
import com.atef.athlete.infrastructure.data.model.user.RoleExtractor;
import org.springframework.stereotype.Repository;

@Repository
public class UserDataRepository implements Users {

    private final UserJpaRepository jpaRepository;

    public UserDataRepository(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void create(RegisteredUser user) {
        jpaRepository.save(RegisteredUserDataModel.from(user));
    }

    @Override
    public RegisteredUser by(UserId id) {
        return jpaRepository
                .findById(id.rawId)
                .map(userModel -> userModel.toRegisteredUser(this::extractRole))
                .orElseThrow(() -> EntityNotFoundException.from(id, RegisteredUser.class));
    }

    private Role extractRole(RoleDataModel roleDataModel) {
        return RoleExtractor.extractRoleFromName(roleDataModel.getType(), roleDataModel.getPayload());
    }
}

