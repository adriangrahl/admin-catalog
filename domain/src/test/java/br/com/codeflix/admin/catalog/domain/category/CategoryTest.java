package br.com.codeflix.admin.catalog.domain.category;

import br.com.codeflix.admin.catalog.domain.exceptions.DomainException;
import br.com.codeflix.admin.catalog.domain.validation.handler.ThrowsValidationHandler;
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

    @Test
    public void givenAnInvalidNullName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectedName = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        final var actualException = Assertions.assertThrows(DomainException.class, ()-> actualCategory.validate(new ThrowsValidationHandler()));

        assertThat(expectedErrorCount).isEqualTo(actualException.getErrors().size());
        assertThat(expectedErrorMessage).isEqualTo(actualException.getErrors().get(0).message());
    }
}
