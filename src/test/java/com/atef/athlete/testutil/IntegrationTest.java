package com.atef.athlete.testutil;

import com.atef.athlete.infrastructure.application.AthleteSpringApplication;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Tag(TestGroup.INTEGRATION_TEST)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {AthleteSpringApplication.class})
@AutoConfigureTestDatabase(replace = NONE)
@Testcontainers
public abstract class IntegrationTest {

    @Autowired
    private Flyway flyway;

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13")
            .withUsername("psUserForTests")
            .withPassword("psPasswordForTests")
            .withDatabaseName("test_athlete_data");

    @DynamicPropertySource
    static void updateDatasourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    }

    @BeforeAll
    public static void initDatabaseForTests() {
        postgreSQLContainer.start();
    }

    @AfterAll
    public static void stopDatabaseForTests() {
        postgreSQLContainer.stop();
    }

    @BeforeEach
    public void cleanDatabase() {
        flyway.clean();
        flyway.migrate();
    }
}
