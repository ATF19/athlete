package com.atef.athlete.infrastructure.data.repository.user;

import com.atef.athlete.domain.model.user.Birthdate;
import com.atef.athlete.domain.model.user.RegisteredUser;
import com.atef.athlete.domain.model.user.RegisteredUserBuilder;
import com.atef.athlete.domain.service.user.Users;
import com.atef.athlete.testutil.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class UserDataRepositoryTest extends IntegrationTest {

    @Autowired
    private Users users;

    @Test
    public void can_save_and_retrieve_user() {
        // given
        RegisteredUser registeredUser = RegisteredUserBuilder.aSuperAdmin()
                .withBirthdate(new Birthdate(new Date()))
                .build();

        // when
        users.create(registeredUser);

        // then
        assertThat(users.by(registeredUser.id())).isEqualTo(registeredUser);
    }

}