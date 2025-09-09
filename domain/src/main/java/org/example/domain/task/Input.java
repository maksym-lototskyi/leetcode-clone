package org.example.domain.task;

import java.util.List;
import java.util.Objects;

public final class Input {
    private final List<String> params;

    public Input (List<String> params, IOValidator validator) {
        Objects.requireNonNull(params, "Input value cannot be null");
        Objects.requireNonNull(validator, "Validator cannot be null");

        if (params.isEmpty()) {
            throw new IllegalArgumentException("Input value cannot be null");
        }
        for (String value : params) {
            if (!validator.isValid(value)) {
                throw new IllegalArgumentException("Invalid format of input data");
            }
        }
        this.params = List.copyOf(params);
    }

    public List<String> params(){
        return List.copyOf(params);
    }
}

