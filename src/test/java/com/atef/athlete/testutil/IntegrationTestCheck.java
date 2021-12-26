package com.atef.athlete.testutil;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationTestCheck implements IntegrationTest {
    @Test
    public void integration_test_are_running_with_integration_test_suite() {
        // given

        // when

        // then
        assertThat(true).isTrue();
    }
}
