package org.example.infrastructure.adapters.test_runner.docker;

public class ProcessInterruptedException extends RuntimeException{
    public ProcessInterruptedException(String message) {
        super(message);
    }

    public ProcessInterruptedException(String message, Throwable cause) {
        super(message, cause);
    }
}
