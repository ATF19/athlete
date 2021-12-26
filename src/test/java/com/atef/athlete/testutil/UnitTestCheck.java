package com.atef.athlete.testutil;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UnitTestCheck implements UnitTest {
    @Test
    public void unit_test_are_running_with_unit_test_suite() {
        // given

        // when

        // then
        assertThat(true).isTrue();
    }
}
