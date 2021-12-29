package com.atef.athlete.infrastructure.data.model.user;

import com.atef.athlete.domain.model.user.role.Role;
import com.atef.athlete.domain.model.user.role.SuperAdmin;
import com.atef.athlete.testutil.UnitTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoleDataModelTest extends UnitTest {

    @Test
    public void create_model_from_role() {
        // given
        Role role = new SuperAdmin();

        // when
        RoleDataModel roleDataModel = RoleDataModel.from(role);

        // then
        assertThat(roleDataModel.getType()).isEqualTo(SuperAdmin.class.getName());
        assertThat(roleDataModel.getPayload()).isNull();
    }
}