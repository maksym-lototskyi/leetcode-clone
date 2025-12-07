package org.example.domain.model.topic;

import org.example.domain.validation.ValidationUtils;

import java.util.UUID;

public record TopicId(UUID value) {
    public TopicId {
        ValidationUtils.requireNonNull(value, "TopicId value cannot be null");
    }

    public static TopicId generate() {
        return new TopicId(UUID.randomUUID());
    }

    public static TopicId of(UUID value) {return new TopicId(value);}
}
