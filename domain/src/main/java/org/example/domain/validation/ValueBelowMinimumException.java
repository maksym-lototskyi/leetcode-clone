package org.example.domain.validation;

public class ValueBelowMinimumException extends RuntimeException{
    public ValueBelowMinimumException(String message) {
        super(message);
    }
}
