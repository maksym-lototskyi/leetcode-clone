package org.example.infrastructure.exception;

public class UnknownRuntimeException extends RuntimeException {
    public UnknownRuntimeException(String message) {
        super(message);
    }
}
