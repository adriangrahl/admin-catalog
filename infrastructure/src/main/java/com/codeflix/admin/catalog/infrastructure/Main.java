package com.codeflix.admin.catalog.infrastructure;

import com.codeflix.admin.catalog.domain.category.Category;
import com.codeflix.admin.catalog.infrastructure.category.persistence.CategoryJpaEntity;
import com.codeflix.admin.catalog.infrastructure.category.persistence.CategoryRepository;
import com.codeflix.admin.catalog.infrastructure.configuration.WebServerConfiguration;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.AbstractEnvironment;

import java.util.List;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "development");
        SpringApplication.run(WebServerConfiguration.class, args);
    }

    @Bean
    public ApplicationRunner runner(CategoryRepository repository) {
        return args -> {
            List<CategoryJpaEntity> all = repository.findAll();

            Category filmes = Category.newCategory("filmes", null, true);

            repository.saveAndFlush(CategoryJpaEntity.from(filmes));
            repository.deleteAll();
        };
    }

}