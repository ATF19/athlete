package com.atef.athlete.infrastructure.data.model.user;

import com.atef.athlete.domain.model.core.EntityStatus;
import com.atef.athlete.domain.model.core.UpdatedBy;
import com.atef.athlete.domain.model.core.UpdatedOn;
import com.atef.athlete.domain.model.user.*;
import com.atef.athlete.domain.model.user.role.Role;
import com.atef.athlete.domain.model.user.role.Roles;
import com.atef.athlete.infrastructure.data.model.AbstractEntityDataModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
@Table(name = "USERS")
@Data
@EqualsAndHashCode(callSuper = true)
public class RegisteredUserDataModel extends AbstractEntityDataModel {

    @Column(name = "USERNAME", nullable = false, updatable = false, unique = true)
    private String username;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "BIRTH_DATE")
    private Date birthdate;

    @Column(name = "PASSWORD", nullable = false)
    private String encryptedPassword;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", nullable = false)
    private Set<RoleDataModel> roles;

    public RegisteredUserDataModel() {
    }

    private RegisteredUserDataModel(UUID id, EntityStatus status, Instant createdOn, String createdBy, Instant updatedOn, String updatedBy, String username, String email, String firstName,
                                    String lastName, Date birthdate, String encryptedPassword, Set<RoleDataModel> roles) {
        super(id, status, createdOn, createdBy, updatedOn, updatedBy);
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.encryptedPassword = encryptedPassword;
        this.roles = roles;
    }

    public static RegisteredUserDataModel from(RegisteredUser user) {
        Set<RoleDataModel> roles = user.roles()
                .map(RoleDataModel::from)
                .collect(Collectors.toSet());
        return new RegisteredUserDataModel(
                user.id().rawId, user.status(), user.createdOn().rawCreatedOn(), user.createdBy().username().rawUsername(),
                user.updatedOn().map(UpdatedOn::rawUpdatedOn).orElse(null),
                user.updatedBy().map(UpdatedBy::username).map(Username::rawUsername).orElse(null),
                user.username().rawUsername(), user.email().rawEmail(), user.name().firstName(), user.name().lastName(),
                user.birthdate().map(Birthdate::rawBirthdate).orElse(null), user.password().rawEncryptedPassword(), roles
        );
    }

    public RegisteredUser toRegisteredUser(Function<RoleDataModel, Role> roleExtractor) {
        return RegisteredUser.create(
                new UserId(getId()),
                getStatus(),
                createdOn(),
                createdBy(),
                updatedOn(),
                updatedBy(),
                new Username(username),
                new Email(email),
                new Name(firstName, lastName),
                Optional.ofNullable(birthdate).map(Birthdate::new),
                new EncryptedPassword(getEncryptedPassword()),
                extractRoles(roleExtractor)
        );
    }

    private Roles extractRoles(Function<RoleDataModel, Role> roleExtractor) {
        if (roles == null || roles.isEmpty())
            return Roles.empty();

        Set<Role> mappedRoles = roles.stream().map(roleExtractor).collect(Collectors.toSet());
        return Roles.from(mappedRoles);
    }
}
