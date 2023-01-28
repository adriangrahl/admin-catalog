package br.com.codeflix.admin.catalog.domain.exceptions;

import br.com.codeflix.admin.catalog.domain.validation.Error;
import java.util.List;

public class DomainException extends RuntimeException {

    private final List<Error> errors;

    private DomainException(final List<Error> theErrors) {
        super("", null, true, false);
        this.errors = theErrors;
    }

    public static DomainException with(final List<Error> theErrors) {
        return new DomainException(theErrors);
    }

    public List<Error> getErrors() {
        return errors;
    }

}
