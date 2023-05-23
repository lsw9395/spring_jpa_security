package com.nhnacademy.spring.jpa_security.exception;

import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

public class ValidationFailedException extends RuntimeException {
    public ValidationFailedException(BindingResult bindingResult) {
        super(bindingResult.getAllErrors().stream()
                .map(error -> new StringBuilder()
                        .append("ObjectName = ")
                        .append(error.getObjectName())
                        .append(", Message = ")
                        .append(error.getDefaultMessage()))
                .collect(Collectors.joining(" | ")));
    }
}
