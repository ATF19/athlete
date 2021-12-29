package com.atef.athlete.domain.service.user;

import com.atef.athlete.domain.model.user.Email;
import com.atef.athlete.domain.model.user.RegisteredUser;
import com.atef.athlete.domain.model.user.UserId;
import com.atef.athlete.domain.model.user.Username;

import java.util.Optional;

public interface Users {
    void create(RegisteredUser user);

    RegisteredUser by(UserId id);

    Optional<RegisteredUser> byUsernameOrEmail(Username username, Email email);
}
