package ru.danil.rest_practice.rest_proj.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ValidationErrorFormatter {
    private ValidationErrorFormatter() {
    }

    public static String extractMessageFromErrors(BindingResult bindingResult){
        StringBuilder errors = new StringBuilder();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.forEach(
                error -> errors
                        .append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";")
        );

        return errors.toString();
    }
}
