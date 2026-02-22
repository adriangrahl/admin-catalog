package com.codeflix.admin.catalog.infrastructure.category;

import com.codeflix.admin.catalog.infrastructure.MySQLGatewayTest;
import com.codeflix.admin.catalog.infrastructure.category.persistence.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@MySQLGatewayTest
public class CategoryMySQLGatewayTest {

    @Autowired
    private CategoryMySQLGateway categoryGateway;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testInjectedDependencies() {
        Assertions.assertNotNull(this.categoryGateway);
        Assertions.assertNotNull(this.categoryRepository);
    }

}
