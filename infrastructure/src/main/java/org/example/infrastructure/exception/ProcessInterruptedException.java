package org.example.infrastructure.exception;

public class ProcessInterruptedException extends RuntimeException{
    public ProcessInterruptedException(String message) {
        super(message);
    }
}
