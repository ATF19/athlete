package com.atef.athlete.infrastructure.data.model.user;

import com.atef.athlete.domain.model.core.UpdatedOn;
import com.atef.athlete.domain.model.user.Birthdate;
import com.atef.athlete.domain.model.user.RegisteredUser;
import com.atef.athlete.domain.model.user.RegisteredUserBuilder;
import com.atef.athlete.domain.model.user.role.Roles;
import com.atef.athlete.domain.model.user.role.SuperAdmin;
import com.atef.athlete.testutil.UnitTest;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class RegisteredUserDataModelTest extends UnitTest {

    @Test
    public void build_model_from_user() {
        // given
        RegisteredUser registeredUser = RegisteredUserBuilder.aSuperAdmin()
                .withBirthdate(new Birthdate(new Date()))
                .withRoles(Roles.from(new SuperAdmin()))
                .build();

        // when
        RegisteredUserDataModel userDataModel = RegisteredUserDataModel.from(registeredUser);

        // then
        assertThat(userDataModel.getId()).isEqualTo(registeredUser.id().rawId);
        assertThat(userDataModel.getStatus()).isEqualTo(registeredUser.status());
        assertThat(userDataModel.getCreatedBy()).isEqualTo(registeredUser.createdBy().username().rawUsername());
        assertThat(userDataModel.getCreatedOn()).isEqualTo(registeredUser.createdOn().rawCreatedOn());
        assertThat(userDataModel.getUpdatedBy()).isEqualTo(registeredUser.updatedBy().map(u -> u.username().rawUsername()).orElse(null));
        assertThat(userDataModel.getUpdatedOn()).isEqualTo(registeredUser.updatedOn().map(UpdatedOn::rawUpdatedOn).orElse(null));
        assertThat(userDataModel.getUsername()).isEqualTo(registeredUser.username().rawUsername());
        assertThat(userDataModel.getEmail()).isEqualTo(registeredUser.email().rawEmail());
        assertThat(userDataModel.getFirstName()).isEqualTo(registeredUser.name().firstName());
        assertThat(userDataModel.getLastName()).isEqualTo(registeredUser.name().lastName());
        assertThat(userDataModel.getBirthdate()).isEqualTo(registeredUser.birthdate().map(Birthdate::rawBirthdate).orElseThrow());
        assertThat(userDataModel.getEncryptedPassword()).isEqualTo(registeredUser.password().rawEncryptedPassword());
        RoleDataModel[] expectedRoles = registeredUser.roles().map(RoleDataModel::from).toArray(RoleDataModel[]::new);
        assertThat(userDataModel.getRoles()).containsExactlyInAnyOrder(expectedRoles);
    }

    @Test
    public void build_model_from_user_without_roles_and_without_birthday() {
        // given
        RegisteredUser registeredUser = RegisteredUserBuilder.aSuperAdmin().withoutRoles().withoutBirthday().build();

        // when
        RegisteredUserDataModel userDataModel = RegisteredUserDataModel.from(registeredUser);

        // then
        assertThat(userDataModel.getId()).isEqualTo(registeredUser.id().rawId);
        assertThat(userDataModel.getStatus()).isEqualTo(registeredUser.status());
        assertThat(userDataModel.getCreatedBy()).isEqualTo(registeredUser.createdBy().username().rawUsername());
        assertThat(userDataModel.getCreatedOn()).isEqualTo(registeredUser.createdOn().rawCreatedOn());
        assertThat(userDataModel.getUpdatedBy()).isEqualTo(registeredUser.updatedBy().map(u -> u.username().rawUsername()).orElse(null));
        assertThat(userDataModel.getUpdatedOn()).isEqualTo(registeredUser.updatedOn().map(UpdatedOn::rawUpdatedOn).orElse(null));
        assertThat(userDataModel.getUsername()).isEqualTo(registeredUser.username().rawUsername());
        assertThat(userDataModel.getEmail()).isEqualTo(registeredUser.email().rawEmail());
        assertThat(userDataModel.getFirstName()).isEqualTo(registeredUser.name().firstName());
        assertThat(userDataModel.getLastName()).isEqualTo(registeredUser.name().lastName());
        assertThat(userDataModel.getBirthdate()).isEqualTo(null);
        assertThat(userDataModel.getEncryptedPassword()).isEqualTo(registeredUser.password().rawEncryptedPassword());
        assertThat(userDataModel.getRoles()).isEmpty();
    }

    @Test
    public void build_user_from_model() {
        // given
        RegisteredUser registeredUser = RegisteredUserBuilder.aSuperAdmin()
                .withBirthdate(new Birthdate(new Date()))
                .withRoles(Roles.from(new SuperAdmin()))
                .build();
        RegisteredUserDataModel userDataModel = RegisteredUserDataModel.from(registeredUser);

        // when
        RegisteredUser result = userDataModel.toRegisteredUser(d -> new SuperAdmin());

        // then
        assertThat(result).isEqualTo(registeredUser);
    }

    @Test
    public void build_user_from_model_without_roles_and_without_birthday() {
        // given
        RegisteredUser registeredUser = RegisteredUserBuilder.aRegisteredUser().withoutRoles().withoutBirthday().build();
        RegisteredUserDataModel userDataModel = RegisteredUserDataModel.from(registeredUser);
        userDataModel.setRoles(null);

        // when
        RegisteredUser result = userDataModel.toRegisteredUser(d -> new SuperAdmin());

        // then
        assertThat(result).isEqualTo(registeredUser);
    }
}