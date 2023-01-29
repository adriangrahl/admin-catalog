package br.com.codeflix.admin.catalog.domain.category;

import br.com.codeflix.admin.catalog.domain.exceptions.DomainException;
import br.com.codeflix.admin.catalog.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

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


    @Test
    public void givenAnInvalidEmptyName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectedName = "  ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        final var actualException = Assertions.assertThrows(DomainException.class, ()-> actualCategory.validate(new ThrowsValidationHandler()));

        assertThat(expectedErrorCount).isEqualTo(actualException.getErrors().size());
        assertThat(expectedErrorMessage).isEqualTo(actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNameLengthMoreThan255_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectedName = """
                Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam 
                rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. 
                Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni 
                dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, 
                consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?
                """;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characteres";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        final var actualException = Assertions.assertThrows(DomainException.class, ()-> actualCategory.validate(new ThrowsValidationHandler()));

        assertThat(expectedErrorCount).isEqualTo(actualException.getErrors().size());
        assertThat(expectedErrorMessage).isEqualTo(actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNameLengthLessThan3_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectedName = " Te   ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characteres";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        final var actualException = Assertions.assertThrows(DomainException.class, ()-> actualCategory.validate(new ThrowsValidationHandler()));

        assertThat(expectedErrorCount).isEqualTo(actualException.getErrors().size());
        assertThat(expectedErrorMessage).isEqualTo(actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAValidFalseIsActive_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = false;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        Assertions.assertDoesNotThrow(()-> actualCategory.validate(new ThrowsValidationHandler()));

        assertThat(actualCategory).isNotNull();
        assertThat(actualCategory.getId()).isNotNull();
        assertThat(expectedName).isEqualTo(actualCategory.getName());
        assertThat(expectedDescription).isEqualTo(actualCategory.getDescription());
        assertThat(expectedIsActive).isEqualTo(actualCategory.isActive());
        assertThat(actualCategory.getCreatedAt()).isNotNull();
        assertThat(actualCategory.getUpdatedAt()).isNotNull();
        assertThat(actualCategory.getDeletedAt()).isNotNull();
    }

    @Test
    public void givenAValidActiveCategory_whenCallDeactivate_thenReturnCategoryInactivated() throws InterruptedException {
        final String expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = false;

        final var aCategory = Category.newCategory(expectedName, expectedDescription, true);
        Assertions.assertDoesNotThrow(()-> aCategory.validate(new ThrowsValidationHandler()));

        Thread.sleep(1);

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();

        assertThat(aCategory.isActive()).isTrue();
        assertThat(aCategory.getDeletedAt()).isNull();

        final var actualCategory = aCategory.deactivate();

        assertThat(actualCategory.getId()).isEqualTo(aCategory.getId());
        assertThat(actualCategory.getName()).isEqualTo(expectedName);
        assertThat(actualCategory.getDescription()).isEqualTo(expectedDescription);
        assertThat(expectedIsActive).isEqualTo(actualCategory.isActive());
        assertThat(actualCategory.getCreatedAt()).isEqualTo(createdAt);
        assertThat(actualCategory.getUpdatedAt()).isAfter(updatedAt);
        assertThat(actualCategory.getDeletedAt()).isNotNull();
    }

    @Test
    public void givenAValidInactiveCategory_whenCallActivate_thenReturnCategoryActivated() throws InterruptedException {
        final String expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var aCategory = Category.newCategory(expectedName, expectedDescription, false);
        Assertions.assertDoesNotThrow(()-> aCategory.validate(new ThrowsValidationHandler()));

        Thread.sleep(1);

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();

        assertThat(aCategory.isActive()).isFalse();
        assertThat(aCategory.getDeletedAt()).isNotNull();

        final var actualCategory = aCategory.activate();

        assertThat(actualCategory.getId()).isEqualTo(aCategory.getId());
        assertThat(actualCategory.getName()).isEqualTo(expectedName);
        assertThat(actualCategory.getDescription()).isEqualTo(expectedDescription);
        assertThat(actualCategory.getCreatedAt()).isEqualTo(createdAt);
        assertThat(expectedIsActive).isEqualTo(actualCategory.isActive());
        assertThat(actualCategory.getUpdatedAt()).isAfter(updatedAt);
        assertThat(actualCategory.getDeletedAt()).isNull();
    }

    @Test
    public void givenAValidCategory_whenCallUpdate_thenReturnCategoryUpdated() throws InterruptedException {
        final String expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var aCategory = Category.newCategory("Original Name", "Original Description", true);
        Assertions.assertDoesNotThrow(()-> aCategory.validate(new ThrowsValidationHandler()));

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();

        Thread.sleep(1);

        final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedIsActive);

        assertThat(actualCategory.getId()).isEqualTo(aCategory.getId());
        assertThat(actualCategory.getName()).isEqualTo(expectedName);
        assertThat(actualCategory.getDescription()).isEqualTo(expectedDescription);
        assertThat(expectedIsActive).isEqualTo(actualCategory.isActive());
        assertThat(actualCategory.getCreatedAt()).isEqualTo(createdAt);
        assertThat(actualCategory.getUpdatedAt()).isAfter(updatedAt);
        assertThat(actualCategory.getDeletedAt()).isNull();
    }

    @Test
    public void givenAValidCategory_whenCallUpdateWithInvalidParams_thenReturnCategoryUpdated() {
        final String expectedName = null;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var aCategory =
                Category.newCategory("Filmes", "A categoria", expectedIsActive);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();

        final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(aCategory.getDeletedAt());
    }

}
