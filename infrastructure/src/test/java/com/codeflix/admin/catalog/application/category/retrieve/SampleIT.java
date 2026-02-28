package com.codeflix.admin.catalog.application.category.retrieve;

import com.codeflix.admin.catalog.IntegrationTest;
import com.codeflix.admin.catalog.application.category.create.CreateCategoryUseCase;
import com.codeflix.admin.catalog.infrastructure.category.persistence.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationTest
public class SampleIT {

    @Autowired
    private CreateCategoryUseCase useCase;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testInjects() {
        Assertions.assertNotNull(this.useCase);
        Assertions.assertNotNull(this.categoryRepository);
    }

}
