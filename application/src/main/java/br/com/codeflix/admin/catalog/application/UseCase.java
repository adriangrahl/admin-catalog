package br.com.codeflix.admin.catalog.application;

import br.com.codeflix.admin.catalog.domain.Category;

public class UseCase {

    public Category execute() {
        return new Category();
    }
}