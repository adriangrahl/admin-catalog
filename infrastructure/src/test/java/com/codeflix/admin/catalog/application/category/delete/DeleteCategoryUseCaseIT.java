package com.codeflix.admin.catalog.application.category.delete;

import com.codeflix.admin.catalog.IntegrationTest;
import com.codeflix.admin.catalog.domain.category.Category;
import com.codeflix.admin.catalog.domain.category.CategoryGateway;
import com.codeflix.admin.catalog.domain.category.CategoryID;
import com.codeflix.admin.catalog.infrastructure.category.persistence.CategoryJpaEntity;
import com.codeflix.admin.catalog.infrastructure.category.persistence.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.stream.Stream;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.eq;

@IntegrationTest
public class DeleteCategoryUseCaseIT {

    @Autowired
    private DeleteCategoryUseCase useCase;

    @Autowired
    private CategoryRepository categoryRepository;

    @SpyBean
    private CategoryGateway categoryGateway;

    @Test
    public void givenAValidId_whenCallsDeleteCategory_thenShouldBeOk() {
        final var aCategory = Category.newCategory("Filmes", "A categoria mais assistida", true);
        final var expectedId = aCategory.getId();

        this.save(aCategory);

        Assertions.assertEquals(1, this.categoryRepository.count());

        Assertions.assertDoesNotThrow(() -> this.useCase.execute(expectedId.getValue()));

        Assertions.assertEquals(0, this.categoryRepository.count());
    }

    @Test
    public void givenAnInvalidId_whenCallsDeleteCategory_thenShouldBeOk() {
        final var expectedId = CategoryID.from("123");
        Assertions.assertDoesNotThrow(() -> this.useCase.execute(expectedId.getValue()));
    }

    @Test
    public void givenAValidId_whenGatewayThrowsException_thenShouldReturnException() {
        final var aCategory = Category.newCategory("Filmes", "A categoria mais assistida", true);
        final var expectedId = aCategory.getId();

        doThrow(new IllegalStateException("Gateway Error"))
                .when(this.categoryGateway).deleteById(eq(expectedId));

        Assertions.assertThrows(IllegalStateException.class, ()-> this.useCase.execute(expectedId.getValue()));
    }

    private void save(final Category... aCategory) {
        this.categoryRepository.saveAll(
                Stream.of(aCategory)
                        .map(CategoryJpaEntity::from)
                        .toList());
    }

}
