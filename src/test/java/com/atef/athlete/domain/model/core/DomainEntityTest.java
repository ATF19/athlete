package com.atef.athlete.domain.model.core;

import com.atef.athlete.testutil.UnitTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DomainEntityTest extends UnitTest {

    @Test
    public void create_entity_without_status() {
        // given
        var id = new TestId();

        // when
        var entity = new TestEntity(id);

        // then
        assertThat(entity.id).isEqualTo(id);
        assertThat(entity.disabled()).isFalse();
        assertThat(entity.deleted()).isFalse();
    }

    @Test
    public void create_entity_with_status() {
        // given
        var id = new TestId();

        // when
        var entity = new TestEntity(id, EntityStatus.DISABLED);

        // then
        assertThat(entity.id).isEqualTo(id);
        assertThat(entity.disabled()).isTrue();
        assertThat(entity.enabled()).isFalse();
        assertThat(entity.deleted()).isFalse();
    }

    @Test
    public void disable_an_entity() {
        // given
        var entity = new TestEntity(new TestId());


        // when
        entity.disable();

        // then
        assertThat(entity.enabled()).isFalse();
        assertThat(entity.disabled()).isTrue();
    }

    @Test
    public void enable_an_entity() {
        // given
        var entity = new TestEntity(new TestId(), EntityStatus.DISABLED);


        // when
        entity.enable();

        // then
        assertThat(entity.enabled()).isTrue();
        assertThat(entity.disabled()).isFalse();
    }

    @Test
    public void delete_an_entity() {
        // given
        var entity = new TestEntity(new TestId());


        // when
        entity.delete();

        // then
        assertThat(entity.deleted()).isTrue();
        assertThat(entity.enabled()).isFalse();
    }

    @Test
    public void enable_a_deleted_entity() {
        // given
        var entity = new TestEntity(new TestId(), EntityStatus.DELETED);


        // when
        entity.enable();

        // then
        assertThat(entity.enabled()).isTrue();
        assertThat(entity.deleted()).isFalse();
    }

    private static class TestId extends Id {
    }

    private static class TestEntity extends DomainEntity<TestId> {
        public TestEntity(TestId id) {
            super(id);
        }

        public TestEntity(TestId id, EntityStatus status) {
            super(id, status);
        }
    }
}