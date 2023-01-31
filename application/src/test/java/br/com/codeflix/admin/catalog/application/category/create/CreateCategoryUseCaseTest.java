package br.com.codeflix.admin.catalog.application.category.create;

import br.com.codeflix.admin.catalog.domain.category.CategoryGateway;
import br.com.codeflix.admin.catalog.domain.exceptions.DomainException;
import br.com.codeflix.admin.catalog.domain.validation.handler.Notification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CreateCategoryUseCaseTest {

    @InjectMocks
    private DefaultCreateCategoryUseCase useCase;

    @Mock
    private CategoryGateway categoryGateway;

    @Test
    public void givenAValidCommand_whenCallsCreateCategory_shouldReturnCategoryId(){
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        Mockito.when(categoryGateway.create(Mockito.any())).thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        assertThat(actualOutput).isNotNull();
        assertThat(actualOutput.id()).isNotNull();

        Mockito.verify(categoryGateway, times(1))
                .create(Mockito.argThat(aCategory ->
                            Objects.equals(expectedName, aCategory.getName()) &&
                            Objects.equals(expectedDescription, aCategory.getDescription()) &&
                            Objects.equals(expectedIsActive, aCategory.isActive()) &&
                            Objects.nonNull(aCategory.getId()) &&
                            Objects.nonNull(aCategory.getCreatedAt()) &&
                            Objects.nonNull(aCategory.getUpdatedAt()) &&
                            Objects.isNull(aCategory.getDeletedAt())));
    }

    @Test
    public void givenAnInvalidName_whenCallsCreateCategory_shouldReturnDomainException(){
        final String expectedName = null;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        final var notification = useCase.execute(aCommand).getLeft();

        assertThat(notification.getErrors().size()).isEqualTo(expectedErrorCount);
        assertThat(notification.firstError().message()).isEqualTo(expectedErrorMessage);
        Mockito.verify(categoryGateway, times(0)).create(Mockito.any());
    }

    @Test
    public void givenAValidCommandWithInactiveCategory_whenCallsCreateCategory_shouldReturnInactiveCategoryId(){
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = false;

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        Mockito.when(categoryGateway.create(Mockito.any())).thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        assertThat(actualOutput).isNotNull();
        assertThat(actualOutput.id()).isNotNull();

        Mockito.verify(categoryGateway, times(1))
                .create(Mockito.argThat(aCategory ->
                        Objects.equals(expectedName, aCategory.getName()) &&
                                Objects.equals(expectedDescription, aCategory.getDescription()) &&
                                Objects.equals(expectedIsActive, aCategory.isActive()) &&
                                Objects.nonNull(aCategory.getId()) &&
                                Objects.nonNull(aCategory.getCreatedAt()) &&
                                Objects.nonNull(aCategory.getUpdatedAt()) &&
                                Objects.nonNull(aCategory.getDeletedAt())));
    }

    @Test
    public void givenAValidCommand_whenGatewayThrowsRandomException_shouldReturnException(){
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "Gateway Error";
        final var expectedErrorCount = 1;

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        Mockito.when(categoryGateway.create(Mockito.any())).thenThrow(new IllegalStateException(expectedErrorMessage));

        final var notification = useCase.execute(aCommand).getLeft();

        assertThat(notification.getErrors().size()).isEqualTo(expectedErrorCount);
        assertThat(notification.firstError().message()).isEqualTo(expectedErrorMessage);

        Mockito.verify(categoryGateway, times(1))
                .create(Mockito.argThat(aCategory ->
                        Objects.equals(expectedName, aCategory.getName()) &&
                                Objects.equals(expectedDescription, aCategory.getDescription()) &&
                                Objects.equals(expectedIsActive, aCategory.isActive()) &&
                                Objects.nonNull(aCategory.getId()) &&
                                Objects.nonNull(aCategory.getCreatedAt()) &&
                                Objects.nonNull(aCategory.getUpdatedAt()) &&
                                Objects.isNull(aCategory.getDeletedAt())));
    }


}
