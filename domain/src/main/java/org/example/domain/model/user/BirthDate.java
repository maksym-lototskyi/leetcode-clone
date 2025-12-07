package org.example.domain.model.user;

import org.example.domain.validation.ValidationUtils;

import java.time.LocalDate;

public record BirthDate (LocalDate value) {
    public BirthDate {
        ValidationUtils.requireNonNull(value, "Birth date cannot be null");
        ValidationUtils.requireNotInFuture(value, "Birth date cannot be in the future");
    }
}
