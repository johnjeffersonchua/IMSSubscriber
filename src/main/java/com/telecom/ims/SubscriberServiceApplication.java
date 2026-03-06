package com.telecom.ims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the IMS Home Phone Subscriber Web Service.
 * * This class configures the Spring Boot environment and initiates the
 * component scan for controllers, services, and repositories.
 */
@SpringBootApplication
public class SubscriberServiceApplication {

    /**
     * The main method that launches the Spring Boot application.
     * * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(SubscriberServiceApplication.class, args);
    }
}