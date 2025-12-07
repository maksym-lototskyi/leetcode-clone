package org.example.domain.model.task;

import org.example.domain.validation.ValidationUtils;

public final class Output {
    private final String value;

    public Output (String value, IOValidator validator) {
        this.value = ValidationUtils.requireNonBlank(value, "Output value cannot be null or blank");
        ValidationUtils.requireNonNull(validator, "IOValidator cannot be null");

        if (!validator.isValid(value)) {
            throw new InvalidIODataFormatException("Output is not valid according to the provided IOValidator");
        }
    }



    public String value() {
        return value;
    }
}

