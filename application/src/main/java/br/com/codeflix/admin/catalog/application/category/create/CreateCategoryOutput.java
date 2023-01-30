package br.com.codeflix.admin.catalog.application.category.create;

import br.com.codeflix.admin.catalog.domain.category.Category;
import br.com.codeflix.admin.catalog.domain.category.CategoryID;

public record CreateCategoryOutput(
        CategoryID id
) {

    public static CreateCategoryOutput from(final Category aCategory) {
        return new CreateCategoryOutput(aCategory.getId());
    }

}
