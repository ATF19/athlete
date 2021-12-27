package com.atef.athlete.domain.model.core;

import com.atef.athlete.domain.model.user.Username;
import com.atef.athlete.testutil.UnitTest;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class DomainEntityTest extends UnitTest {

    @Test
    public void create_entity() {
        // given
        var id = new TestId();
        var createdOn = CreatedOn.now();
        var createdBy = new CreatedBy(new Username("Atef"));

        // when
        var entity = new TestEntity(id, createdOn, createdBy);

        // then
        assertThat(entity.id()).isEqualTo(id);
        assertThat(entity.disabled()).isFalse();
        assertThat(entity.deleted()).isFalse();
        assertThat(entity.createdOn()).isEqualTo(createdOn);
        assertThat(entity.createdBy()).isEqualTo(createdBy);
        assertThat(entity.updatedOn()).isEmpty();
        assertThat(entity.updatedBy()).isEmpty();
    }

    @Test
    public void create_entity_with_status_and_flags() {
        // given
        var id = new TestId();
        var createdOn = CreatedOn.now();
        var createdBy = new CreatedBy(new Username("Atef"));
        var updatedOn = UpdatedOn.now();
        var updatedBy = new UpdatedBy(new Username("AtefDidAnUpdate"));

        // when
        var entity = new TestEntity(id, EntityStatus.DISABLED, createdOn, createdBy, Optional.of(updatedOn), Optional.of(updatedBy));

        // then
        assertThat(entity.id()).isEqualTo(id);
        assertThat(entity.disabled()).isTrue();
        assertThat(entity.enabled()).isFalse();
        assertThat(entity.deleted()).isFalse();
        assertThat(entity.createdOn()).isEqualTo(createdOn);
        assertThat(entity.createdBy()).isEqualTo(createdBy);
        assertThat(entity.updatedOn()).contains(updatedOn);
        assertThat(entity.updatedBy()).contains(updatedBy);
    }

    @Test
    public void disable_an_entity() {
        // given
        var entity = new TestEntity(new TestId());
        var updatedOn = UpdatedOn.now();
        var updatedBy = new UpdatedBy(new Username("Test"));

        // when
        entity.disable(updatedOn, updatedBy);

        // then
        assertThat(entity.enabled()).isFalse();
        assertThat(entity.disabled()).isTrue();
        assertThat(entity.updatedOn()).contains(updatedOn);
        assertThat(entity.updatedBy()).contains(updatedBy);
    }

    @Test
    public void enable_an_entity() {
        // given
        var entity = new TestEntity(new TestId(), EntityStatus.DISABLED);
        var updatedOn = UpdatedOn.now();
        var updatedBy = new UpdatedBy(new Username("Test"));

        // when
        entity.enable(updatedOn, updatedBy);

        // then
        assertThat(entity.enabled()).isTrue();
        assertThat(entity.disabled()).isFalse();
        assertThat(entity.updatedOn()).contains(updatedOn);
        assertThat(entity.updatedBy()).contains(updatedBy);
    }

    @Test
    public void delete_an_entity() {
        // given
        var entity = new TestEntity(new TestId());
        var updatedOn = UpdatedOn.now();
        var updatedBy = new UpdatedBy(new Username("Test"));

        // when
        entity.delete(updatedOn, updatedBy);

        // then
        assertThat(entity.deleted()).isTrue();
        assertThat(entity.enabled()).isFalse();
        assertThat(entity.updatedOn()).contains(updatedOn);
        assertThat(entity.updatedBy()).contains(updatedBy);
    }

    @Test
    public void enable_a_deleted_entity() {
        // given
        var entity = new TestEntity(new TestId(), EntityStatus.DELETED);
        var updatedOn = UpdatedOn.now();
        var updatedBy = new UpdatedBy(new Username("Test"));

        // when
        entity.enable(updatedOn, updatedBy);

        // then
        assertThat(entity.enabled()).isTrue();
        assertThat(entity.deleted()).isFalse();
        assertThat(entity.updatedOn()).contains(updatedOn);
        assertThat(entity.updatedBy()).contains(updatedBy);
    }

    private static class TestId extends Id {
    }

    private static class TestEntity extends DomainEntity<TestId> {
        public TestEntity(TestId id) {
            super(id, CreatedOn.now(), new CreatedBy(new Username("ForTest")));
        }

        public TestEntity(TestId id, EntityStatus status) {
            super(id, status, CreatedOn.now(), new CreatedBy(new Username("ForTest")),
                    Optional.of(UpdatedOn.now()), Optional.of(new UpdatedBy(new Username("ForTest"))));
        }

        public TestEntity(TestId id, CreatedOn createdOn, CreatedBy createdBy) {
            super(id, createdOn, createdBy);
        }

        public TestEntity(TestId id, EntityStatus status, CreatedOn createdOn, CreatedBy createdBy, Optional<UpdatedOn> updatedOn, Optional<UpdatedBy> updatedBy) {
            super(id, status, createdOn, createdBy, updatedOn, updatedBy);
        }
    }
}