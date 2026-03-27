package com.codeflix.admin.catalog.infrastructure.api.controllers;

import com.codeflix.admin.catalog.domain.exceptions.DomainException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.codeflix.admin.catalog.domain.validation.Error;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = DomainException.class )
    public ResponseEntity<?> handleDomainException(final DomainException ex) {
        return ResponseEntity.unprocessableEntity()
                .body(ApiError.from(ex));
    }

    record ApiError(String message, List<Error> errors) {
        static ApiError from(final DomainException ex) {
            return new ApiError(ex.getMessage(), ex.getErrors());
        }
    }

}
