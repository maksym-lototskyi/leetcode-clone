package org.example.domain.validation;

public class ValueAboveMaximumException extends RuntimeException {
    public ValueAboveMaximumException(String message) {
        super(message);
    }
}
