package br.com.codeflix.admin.catalog.application.category.create;

import br.com.codeflix.admin.catalog.domain.category.Category;
import br.com.codeflix.admin.catalog.domain.category.CategoryGateway;
import br.com.codeflix.admin.catalog.domain.validation.handler.ThrowsValidationHandler;

import java.util.Objects;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CreateCategoryOutput execute(final CreateCategoryCommand aCommand) {

        String aName = aCommand.name();
        String aDescription = aCommand.description();
        boolean isActive = aCommand.active();

        final var aCategory = Category.newCategory(aName, aDescription, isActive);
        aCategory.validate(new ThrowsValidationHandler());

        return CreateCategoryOutput.from(this.categoryGateway.create(aCategory));
    }

}
