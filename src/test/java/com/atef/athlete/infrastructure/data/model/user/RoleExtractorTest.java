package com.atef.athlete.infrastructure.data.model.user;

import com.atef.athlete.domain.model.user.role.Role;
import com.atef.athlete.domain.model.user.role.SuperAdmin;
import com.atef.athlete.testutil.UnitTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoleExtractorTest extends UnitTest {

    @Test
    public void extract_super_admin_role() {
        // given

        // when
        Role role = RoleExtractor.extractRoleFromName(SuperAdmin.class.getName(), null);

        // then
        assertThat(role).isEqualTo(new SuperAdmin());
    }

}