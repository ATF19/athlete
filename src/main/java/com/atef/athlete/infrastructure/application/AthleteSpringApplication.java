package com.atef.athlete.infrastructure.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.atef.athlete")
@EnableJpaRepositories(basePackages = "com.atef.athlete.infrastructure.data.repository")
@EntityScan(basePackages = "com.atef.athlete.infrastructure.data.model")
public class AthleteSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(AthleteSpringApplication.class, args);
    }

}
