package org.example.domain.validation;

public class EmptyCollectionException extends RuntimeException {
    public EmptyCollectionException(String message) {
        super(message);
    }
}
