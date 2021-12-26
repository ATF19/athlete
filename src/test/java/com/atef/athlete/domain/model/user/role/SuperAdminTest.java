package com.atef.athlete.domain.model.user.role;

import com.atef.athlete.testutil.UnitTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SuperAdminTest extends UnitTest {

    @Test
    public void can_get_role_name() {
        // given
        var superAdmin = new SuperAdmin();

        // when
        var roleName = superAdmin.roleName();

        // then
        assertThat(roleName).isEqualTo(new RoleName("SuperAdmin"));
    }

    @Test
    public void are_equals_if_is_super_admin() {
        // given
        var superAdmin1 = new SuperAdmin();

        // when
        var superAdmin2 = new SuperAdmin();

        // then
        assertThat(superAdmin2).isEqualTo(superAdmin1);
        assertThat(superAdmin2.hashCode()).isEqualTo(superAdmin1.hashCode());
    }

    @Test
    public void are_not_equal_if_is_not_a_super_admin() {
        // given
        var superAdmin1 = new SuperAdmin();

        // when
        Role anotherType = () -> new RoleName("Test");

        // then
        assertThat(anotherType).isNotEqualTo(superAdmin1);
        assertThat(anotherType.hashCode()).isNotEqualTo(superAdmin1.hashCode());
    }
}