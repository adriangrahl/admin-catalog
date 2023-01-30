package br.com.codeflix.admin.catalog.application.category.create;

public record CreateCategoryCommand(
        String name,
        String description,
        boolean active
) {

    public static CreateCategoryCommand with(
            final String aName,
            final String aDescription,
            final boolean isActive) {
        return new CreateCategoryCommand(aName, aDescription, isActive);
    }

}
