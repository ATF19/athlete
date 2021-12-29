package com.atef.athlete.infrastructure.data.repository.user;

import com.atef.athlete.domain.model.user.*;
import com.atef.athlete.domain.service.user.Users;
import com.atef.athlete.testutil.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Optional;

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

    @Test
    public void empty_if_email_and_username_does_not_exists() {
        // given
        RegisteredUser registeredUser = RegisteredUserBuilder.aSuperAdmin()
                .withBirthdate(new Birthdate(new Date()))
                .build();
        users.create(registeredUser);

        // when
        Optional<RegisteredUser> result = users.byUsernameOrEmail(new Username("Not found"), new Email("notfound@either.com"));

        // then
        assertThat(result).isEmpty();
    }

    @Test
    public void retrieve_user_if_found_by_username() {
        // given
        RegisteredUser registeredUser = RegisteredUserBuilder.aSuperAdmin()
                .withBirthdate(new Birthdate(new Date()))
                .build();
        users.create(registeredUser);

        // when
        Optional<RegisteredUser> result = users.byUsernameOrEmail(registeredUser.username(), new Email("notfound@either.com"));

        // then
        assertThat(result).contains(registeredUser);
    }

    @Test
    public void retrieve_user_if_found_by_email() {
        // given
        RegisteredUser registeredUser = RegisteredUserBuilder.aSuperAdmin()
                .withBirthdate(new Birthdate(new Date()))
                .build();
        users.create(registeredUser);

        // when
        Optional<RegisteredUser> result = users.byUsernameOrEmail(new Username("Not found"), registeredUser.email());

        // then
        assertThat(result).contains(registeredUser);
    }
}