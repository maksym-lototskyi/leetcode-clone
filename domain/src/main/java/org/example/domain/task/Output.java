package org.example.domain.task;

import lombok.Getter;

public final class Output {
    private final String value;

    private Output(String value) {
        this.value = value;
    }

    public static Output of(String value, IOValidator validator) {
        if (value == null) {
            throw new IllegalArgumentException("Output value cannot be null");
        }
        if (!validator.isValid(value)) {
            throw new IllegalArgumentException("Invalid format of output data");
        }
        return new Output(value);
    }

    public String value() {
        return value;
    }
}

