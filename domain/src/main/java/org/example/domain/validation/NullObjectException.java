package org.example.domain.validation;

public class NullObjectException extends RuntimeException {
    public NullObjectException(String message) {
        super(message);
    }
}
