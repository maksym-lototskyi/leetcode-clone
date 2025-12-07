package org.example.domain.validation;

public class DateInFutureException extends RuntimeException {
    public DateInFutureException(String message) {
        super(message);
    }
}
