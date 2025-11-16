package org.example.domain.task;

import org.example.domain.task.service.IOValidator;

import java.util.List;
import java.util.Objects;

public final class Input {
    private final String input;

    public Input (String input, IOValidator validator) {
        this.input = input;
        Objects.requireNonNull(input, "Input name cannot be null");
        Objects.requireNonNull(validator, "Validator cannot be null");

        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input name cannot be empty");
        }
        if(!validator.isValid(input)) {
            throw new IllegalArgumentException("Input name is not valid");
        }
    }

    public String getInput() {
        return input;
    }
}

