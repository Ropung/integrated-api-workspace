package com.joara.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import static com.joara.auth.Constants.ROOT_PACKAGE;

@SpringBootApplication(scanBasePackages = ROOT_PACKAGE)
@ConfigurationPropertiesScan(basePackages = ROOT_PACKAGE)
public class AuthenticationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthenticationServiceApplication.class, args);
    }
}
