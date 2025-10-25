package org.example.infrastructure.persistence.jpa.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class StringListConverter implements AttributeConverter<java.util.List<String>, String> {

    private static final String SPLIT_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(java.util.List<String> stringList) {
        return String.join(SPLIT_CHAR, stringList);
    }

    @Override
    public java.util.List<String> convertToEntityAttribute(String string) {
        return java.util.Arrays.asList(string.split(SPLIT_CHAR));
    }
}
