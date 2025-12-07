package org.example.domain.validation;

public class InvalidComparisonException extends RuntimeException {
    public InvalidComparisonException(String message) {
        super(message);
    }
}
