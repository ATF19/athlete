package com.atef.athlete.infrastructure.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.atef.athlete"})
public class AthleteSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(AthleteSpringApplication.class, args);
    }

}
