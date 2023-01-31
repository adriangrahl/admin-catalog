package br.com.codeflix.admin.catalog.infrastructure;

import br.com.codeflix.admin.catalog.infrastructure.configuration.WebServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(WebServerConfig.class, args);
        System.out.println("Hello world!");
//        System.out.println(new UseCase().execute());
    }
}