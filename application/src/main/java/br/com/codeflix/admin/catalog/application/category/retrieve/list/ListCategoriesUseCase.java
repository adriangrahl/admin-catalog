package br.com.codeflix.admin.catalog.application.category.retrieve.list;


import br.com.codeflix.admin.catalog.application.UseCase;
import br.com.codeflix.admin.catalog.domain.pagination.Pagination;
import br.com.codeflix.admin.catalog.domain.pagination.SearchQuery;

public abstract class ListCategoriesUseCase
        extends UseCase<SearchQuery, Pagination<CategoryListOutput>> {
}
