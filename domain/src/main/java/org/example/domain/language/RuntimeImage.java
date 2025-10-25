package org.example.domain.language;

public record RuntimeImage(String value) {
    public RuntimeImage{
        if(value == null || value.isEmpty()) throw new IllegalArgumentException("Value cannot be null or empty");
    }
}
