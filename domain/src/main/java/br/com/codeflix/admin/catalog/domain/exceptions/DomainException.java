package br.com.codeflix.admin.catalog.domain.exceptions;

import br.com.codeflix.admin.catalog.domain.validation.Error;
import java.util.List;

public class DomainException extends NoStackTraceException {

    private final List<Error> errors;

    private DomainException(final String aMessage, final List<Error> theErrors) {
        super(aMessage);
        this.errors = theErrors;
    }

    public static DomainException with(final Error anError) {
        return new DomainException(anError.message(), List.of(anError));
    }

    public static DomainException with(final List<Error> theErrors) {
        return new DomainException("", theErrors);
    }

    public List<Error> getErrors() {
        return errors;
    }

}
