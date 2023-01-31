package br.com.codeflix.admin.catalog.application.category.create;

import br.com.codeflix.admin.catalog.application.UseCase;
import br.com.codeflix.admin.catalog.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {



}
