package org.example.domain.topic;

import java.util.Objects;

public record Topic (TopicId topicId, String value){
    public Topic {
        Objects.requireNonNull(topicId, "TopicId cannot be null");
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Topic value cannot be null or blank");
        }
    }
}
