package com.atef.athlete.testutil;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseCheckTest extends IntegrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void can_connect_to_db() {
        // given
        String checkSql = "SELECT 1";

        // when

        // then
        jdbcTemplate.execute(checkSql); // No exception if everything is fine
    }

}
