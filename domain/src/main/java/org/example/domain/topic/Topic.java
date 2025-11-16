package org.example.domain.topic;

import java.util.Objects;
import java.util.UUID;

public record Topic (TopicId topicId, String name){
    public Topic {
        Objects.requireNonNull(topicId, "TopicId cannot be null");
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Topic name cannot be null or blank");
        }
    }

    public static Topic of(UUID id, String name) {
        return new Topic(TopicId.of(id), name);
    }
}
