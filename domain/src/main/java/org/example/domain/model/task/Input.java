package org.example.domain.model.task;

import org.example.domain.validation.ValidationUtils;

public final class Input {
    private final String value;

    public Input (String value, IOValidator validator) {
        ValidationUtils.requireNonNull(validator, "IOValidator cannot be null");
        this.value = ValidationUtils.requireNonBlank(value, "Input name cannot be null or blank");

        if(!validator.isValid(value)) {
            throw new InvalidIODataFormatException("Input is not valid according to the provided IOValidator");
        }
    }

    public String value(){
        return value;
    }

}

