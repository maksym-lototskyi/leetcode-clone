package org.example.application.language.use_cases.create;

public class LanguageAlreadyExistsException extends RuntimeException {
    public LanguageAlreadyExistsException(String message) {
        super(message);
    }
}
