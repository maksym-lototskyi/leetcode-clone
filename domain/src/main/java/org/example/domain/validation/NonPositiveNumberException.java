package org.example.domain.validation;

public class NonPositiveNumberException extends RuntimeException {
    public NonPositiveNumberException(String message) {
        super(message);
    }
}
