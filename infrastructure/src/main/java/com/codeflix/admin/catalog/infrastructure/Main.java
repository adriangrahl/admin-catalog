package com.codeflix.admin.catalog.infrastructure;

import com.codeflix.admin.catalog.infrastructure.configuration.WebServerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        SpringApplication.run(WebServerConfiguration.class, args);
    }

}