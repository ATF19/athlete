package com.atef.athlete.infrastructure.application;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class AthleteApplicationListener {
    private static final Logger logger = Logger.getLogger(AthleteApplicationListener.class.getName());

    @EventListener(ApplicationReadyEvent.class)
    public void applicationStarted() {
        logger.info("Athlete is ready to receive requests.");
    }

}
