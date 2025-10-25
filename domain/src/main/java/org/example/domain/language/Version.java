package org.example.domain.language;

public record Version (String value){
    public Version{
        if(value == null || value.isEmpty()) throw new IllegalArgumentException("Value cannot be null or empty");
    }
}
