package com.management.system.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class GatewayLauncher {
    public static void main(String[] args) {
        SpringApplication.run(GatewayLauncher.class, args);
    }

}