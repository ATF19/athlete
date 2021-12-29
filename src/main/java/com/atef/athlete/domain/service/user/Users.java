package com.atef.athlete.domain.service.user;

import com.atef.athlete.domain.model.user.RegisteredUser;
import com.atef.athlete.domain.model.user.UserId;

public interface Users {
    void create(RegisteredUser user);

    RegisteredUser by(UserId id);
}
