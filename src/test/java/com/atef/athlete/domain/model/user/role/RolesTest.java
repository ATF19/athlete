package com.atef.athlete.domain.model.user.role;

import com.atef.athlete.testutil.UnitTest;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class RolesTest extends UnitTest {

    @Test
    public void create_empty_roles() {
        // given

        // when
        var roles = Roles.empty();

        // then
        assertThat(roles.grantedRoles()).isEmpty();
    }

    @Test
    public void create_roles() {
        // given
        var superAdmin = new SuperAdmin();
        Role dummyRole = () -> new RoleName("Dummy role");

        // when
        var roles = Roles.from(superAdmin, dummyRole);

        // then
        assertThat(roles.grantedRoles()).containsExactlyInAnyOrder(superAdmin, dummyRole);
    }

    @Test
    public void create_roles_from_list() {
        // given
        SuperAdmin superAdmin = new SuperAdmin();
        Role dummyRole = () -> new RoleName("Dummy role");
        Set<Role> listOfRoles = Set.of(superAdmin, dummyRole);

        // when
        var roles = Roles.from(listOfRoles);

        // then
        assertThat(roles.grantedRoles()).containsExactlyInAnyOrder(superAdmin, dummyRole);
    }

    @Test
    public void grant_a_role() {
        // given
        SuperAdmin superAdmin = new SuperAdmin();
        Set<Role> listOfRoles = Set.of(superAdmin);
        var roles = Roles.from(listOfRoles);
        Role newRole = () -> new RoleName("Test");

        // when
        roles.grant(newRole);

        // then
        assertThat(roles.grantedRoles())
                .containsExactlyInAnyOrder(superAdmin, newRole);
    }

    @Test
    public void can_grant_the_same_role_multiple_times() {
        // given
        SuperAdmin superAdmin = new SuperAdmin();
        var roles = Roles.from(superAdmin);

        // when
        roles.grant(new SuperAdmin());

        // then
        assertThat(roles.grantedRoles())
                .hasSize(1)
                .containsOnly(superAdmin);
    }

    @Test
    public void revoke_a_role() {
        // given
        SuperAdmin superAdmin = new SuperAdmin();
        Role dummyRole = () -> new RoleName("Test");
        Set<Role> listOfRoles = Set.of(superAdmin, dummyRole);
        var roles = Roles.from(listOfRoles);

        // when
        roles.revoke(dummyRole);

        // then
        assertThat(roles.grantedRoles()).containsOnly(superAdmin);
    }

    @Test
    public void can_revoke_an_unexisting_role() {
        // given
        Role dummyRole = () -> new RoleName("Test");
        var roles = Roles.from(dummyRole);

        // when
        roles.revoke(new SuperAdmin());

        // then
        assertThat(roles.grantedRoles()).containsOnly(dummyRole);
    }
}