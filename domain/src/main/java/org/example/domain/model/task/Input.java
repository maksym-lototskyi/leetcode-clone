package org.example.domain.model.task;

import lombok.Getter;
import org.example.domain.validation.ValidationUtils;

@Getter
public final class Input {
    private final String input;

    public Input (String input, IOValidator validator) {
        ValidationUtils.requireNonNull(validator, "IOValidator cannot be null");
        this.input = ValidationUtils.requireNonBlank(input, "Input name cannot be null or blank");

        if(!validator.isValid(input)) {
            throw new InvalidIODataFormatException("Input is not valid according to the provided IOValidator");
        }
    }

}

