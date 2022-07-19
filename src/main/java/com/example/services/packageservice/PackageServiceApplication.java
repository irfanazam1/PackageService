package com.example.services.packageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// just checking a new image version
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class PackageServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PackageServiceApplication.class, args);
    }
}
