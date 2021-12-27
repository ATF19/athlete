package com.atef.athlete.domain.model.core;

import com.atef.athlete.testutil.UnitTest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class IdTest extends UnitTest {

    @Test
    public void are_equals() {
        // given
        var rawUuid = UUID.randomUUID();
        var id1 = new TestId(rawUuid);

        // when
        var id2 = new TestId(rawUuid);

        // then
        assertThat(id2).isEqualTo(id1);
        assertThat(id2.hashCode()).isEqualTo(id1.hashCode());
    }

    @Test
    public void are_not_equal() {
        // given
        var rawUuid1 = UUID.randomUUID();
        var rawUuid12 = UUID.randomUUID();
        var id1 = new TestId(rawUuid1);

        // when
        var id2 = new TestId(rawUuid12);

        // then
        assertThat(id2).isNotEqualTo(id1);
        assertThat(id2.hashCode()).isNotEqualTo(id1.hashCode());
    }

    private static class TestId extends Id {
        public TestId() {
        }

        public TestId(UUID uuid) {
            super(uuid);
        }
    }

}