package org.example.domain.topic;

public record Topic (String value){
    public Topic {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Topic value cannot be null or blank");
        }
    }
}
