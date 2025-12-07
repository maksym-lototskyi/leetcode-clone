package org.example.domain.validation;

public class ConditionNotMetException extends RuntimeException {
    public ConditionNotMetException(String message) {
        super(message);
    }
}
