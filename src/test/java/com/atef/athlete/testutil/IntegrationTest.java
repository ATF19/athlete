package com.atef.athlete.testutil;

import com.atef.athlete.infrastructure.application.AthleteSpringApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
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
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @BeforeEach
    public void initDatabaseForTests() {
        postgreSQLContainer.start();
    }

    @AfterEach
    public void stopDatabaseForTests() {
        postgreSQLContainer.stop();
    }

}
