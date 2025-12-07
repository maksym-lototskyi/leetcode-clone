package org.example.domain.model.topic;

import org.example.domain.validation.ValidationUtils;

import java.util.Objects;
import java.util.UUID;

public record Topic (TopicId topicId, String name){
    public Topic {
        ValidationUtils.requireNonNull(topicId, "TopicId cannot be null");
        ValidationUtils.requireNonBlank(name, "Topic name cannot be null or blank");
    }

    public static Topic of(UUID id, String name) {
        return new Topic(TopicId.of(id), name);
    }
}
