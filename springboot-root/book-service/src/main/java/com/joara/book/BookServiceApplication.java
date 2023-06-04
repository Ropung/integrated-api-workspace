package com.joara.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import static com.joara.Constants.ROOT_PACKAGE;

@SpringBootApplication(scanBasePackages = ROOT_PACKAGE)
@ConfigurationPropertiesScan(basePackages = ROOT_PACKAGE)
public class BookServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookServiceApplication.class, args);
    }
}
