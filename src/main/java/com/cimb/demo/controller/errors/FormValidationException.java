package com.cimb.demo.controller.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;

import java.util.Map;

@SuppressWarnings("java:S110") // Inheritance tree of classes should not be too deep
public class FormValidationException extends ErrorResponseException {

    public FormValidationException(String defaultMessage, String errorKey, Map<String, Object> errors) {
        super(
                HttpStatus.BAD_REQUEST,
                ProblemDetailWithCause.ProblemDetailWithCauseBuilder
                        .instance()
                        .withStatus(HttpStatus.BAD_REQUEST.value())
                        .withTitle(defaultMessage)
                        .withProperty("message", "error." + errorKey)
                        .withProperty("fields", errors)
                        .build(),
                null
        );

    }

    public ProblemDetailWithCause getProblemDetailWithCause() {
        return (ProblemDetailWithCause) this.getBody();
    }
}
