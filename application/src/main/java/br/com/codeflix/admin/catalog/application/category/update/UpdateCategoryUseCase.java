package br.com.codeflix.admin.catalog.application.category.update;

import br.com.codeflix.admin.catalog.application.UseCase;
import br.com.codeflix.admin.catalog.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase
        extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {
}
