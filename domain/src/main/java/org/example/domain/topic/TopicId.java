package org.example.domain.topic;

import java.util.UUID;

public record TopicId(UUID value) {
    public TopicId {
        if (value == null) {
            throw new IllegalArgumentException("TopicId value cannot be null");
        }
    }

    public static TopicId generate() {
        return new TopicId(UUID.randomUUID());
    }
}
