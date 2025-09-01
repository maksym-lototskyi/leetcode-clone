package org.example.domain.task;

import lombok.Getter;

public final class Input {
    private final String value;

    private Input(String value) {
        this.value = value;
    }

    public static Input of(String value, IOValidator validator) {
        if (value == null) {
            throw new IllegalArgumentException("Input value cannot be null");
        }
        if (!validator.isValid(value)) {
            throw new IllegalArgumentException("Invalid format of input data");
        }
        return new Input(value);
    }

    public String value() {
        return value;
    }
}

