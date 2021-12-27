package com.atef.athlete.domain.model.user;

import com.atef.athlete.domain.model.core.*;
import com.atef.athlete.domain.model.user.role.Role;
import com.atef.athlete.domain.model.user.role.Roles;

import java.util.Optional;
import java.util.stream.Stream;

public class RegisteredUser extends DomainEntity<UserId> {
    public final Username username;
    private Email email;
    private Name name;
    private Optional<Birthdate> birthdate;
    private EncryptedPassword encryptedPassword;
    private Roles roles;

    private RegisteredUser(UserId id, CreatedOn createdOn, CreatedBy createdBy,
                           Username username, Email email, Name name,
                           Optional<Birthdate> birthdate, EncryptedPassword encryptedPassword,
                           Roles roles) {
        super(id, createdOn, createdBy);
        this.username = username;
        this.email = email;
        this.name = name;
        this.birthdate = birthdate;
        this.encryptedPassword = encryptedPassword;
        this.roles = roles;
    }

    private RegisteredUser(UserId id, EntityStatus status, CreatedOn createdOn, CreatedBy createdBy,
                           Optional<UpdatedOn> updatedOn, Optional<UpdatedBy> updatedBy, Username username,
                           Email email, Name name, Optional<Birthdate> birthdate, EncryptedPassword encryptedPassword,
                           Roles roles) {
        super(id, status, createdOn, createdBy, updatedOn, updatedBy);
        this.username = username;
        this.email = email;
        this.name = name;
        this.birthdate = birthdate;
        this.encryptedPassword = encryptedPassword;
        this.roles = roles;
    }

    public static RegisteredUser create(UserId id, CreatedOn createdOn, CreatedBy createdBy,
                                        Username username, Email email, Name name,
                                        Optional<Birthdate> birthdate, EncryptedPassword encryptedPassword,
                                        Roles roles) {
        return new RegisteredUser(id, createdOn, createdBy, username, email, name, birthdate, encryptedPassword, roles);
    }

    public static RegisteredUser create(UserId id, EntityStatus status, CreatedOn createdOn, CreatedBy createdBy,
                                        Optional<UpdatedOn> updatedOn, Optional<UpdatedBy> updatedBy, Username username,
                                        Email email, Name name, Optional<Birthdate> birthdate, EncryptedPassword encryptedPassword,
                                        Roles roles) {
        return new RegisteredUser(id, status, createdOn, createdBy, updatedOn, updatedBy, username, email, name, birthdate, encryptedPassword, roles);
    }

    public Username username() {
        return username;
    }

    public Email email() {
        return email;
    }

    public Name name() {
        return name;
    }

    public Optional<Birthdate> birthdate() {
        return birthdate;
    }

    public EncryptedPassword password() {
        return encryptedPassword;
    }

    public Stream<Role> roles() {
        return roles.grantedRoles();
    }
}