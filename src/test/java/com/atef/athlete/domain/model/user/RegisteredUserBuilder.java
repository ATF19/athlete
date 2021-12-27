package com.atef.athlete.domain.model.user;

import com.atef.athlete.domain.model.core.DomainEntityBuilder;
import com.atef.athlete.domain.model.user.role.Roles;
import com.atef.athlete.domain.model.user.role.SuperAdmin;

import java.util.Optional;

public final class RegisteredUserBuilder extends DomainEntityBuilder<UserId> {
    private Username username;
    private Email email = new Email("createdFrom@builder.com");
    private Name name = new Name("Iam", "a Builder");
    private Optional<Birthdate> birthdate = Optional.empty();
    private EncryptedPassword encryptedPassword = new EncryptedPassword("STRRRROONG");
    private Roles roles = Roles.empty();

    private RegisteredUserBuilder() {
    }

    public static RegisteredUserBuilder aRegisteredUser() {
        return new RegisteredUserBuilder();
    }

    public static RegisteredUserBuilder aSuperAdmin() {
        return new RegisteredUserBuilder()
                .withRoles(Roles.from(new SuperAdmin()));
    }


    public RegisteredUserBuilder withUsername(Username username) {
        this.username = username;
        return this;
    }

    public RegisteredUserBuilder withEmail(Email email) {
        this.email = email;
        return this;
    }

    public RegisteredUserBuilder withName(Name name) {
        this.name = name;
        return this;
    }

    public RegisteredUserBuilder withBirthdate(Optional<Birthdate> birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public RegisteredUserBuilder withEncryptedPassword(EncryptedPassword encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
        return this;
    }

    public RegisteredUserBuilder withRoles(Roles roles) {
        this.roles = roles;
        return this;
    }

    public RegisteredUser build() {
        return RegisteredUser.create(id, status, createdOn, createdBy, updatedOn, updatedBy, username,
                email, name, birthdate, encryptedPassword, roles);
    }

    @Override
    protected UserId defaultId() {
        return new UserId();
    }
}
