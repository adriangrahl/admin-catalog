package br.com.codeflix.admin.catalog.domain.category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryTest {

    //Behavior Driven Design, given-when-then
    @Test
    public void givenAValidParams_whenCallNewCategory_thenInstantiateNewCategory() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        assertThat(actualCategory).isNotNull();
        assertThat(actualCategory.getId()).isNotNull();
        assertThat(expectedName).isEqualTo(actualCategory.getName());
        assertThat(expectedDescription).isEqualTo(actualCategory.getDescription());
        assertThat(expectedIsActive).isEqualTo(actualCategory.isActive());
        assertThat(actualCategory.getCreatedAt()).isNotNull();
        assertThat(actualCategory.getUpdatedAt()).isNotNull();
        assertThat(actualCategory.getDeletedAt()).isNull();

    }
}
