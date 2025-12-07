package org.example.domain.validation;

public class InvalidStringFormatException extends RuntimeException {
    public InvalidStringFormatException(String message) {
        super(message);
    }
}
