package org.example.domain.task;

public final class Output {
    private final String value;

    public Output (String value, IOValidator validator) {
        if (value == null) {
            throw new IllegalArgumentException("Output value cannot be null");
        }
        if (!validator.isValid(value)) {
            throw new IllegalArgumentException("Invalid format of output data");
        }
        this.value = value;
    }



    public String value() {
        return value;
    }
}

