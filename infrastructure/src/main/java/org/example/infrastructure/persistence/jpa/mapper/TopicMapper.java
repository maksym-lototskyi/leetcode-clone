package org.example.infrastructure.persistence.jpa.mapper;

import org.example.domain.topic.Topic;
import org.example.infrastructure.persistence.jpa.model.TopicEntity;

public class TopicMapper {
    public static TopicEntity map(Topic topic){
        return TopicEntity.builder()
                .id(topic.topicId().value())
                .name(topic.name())
                .build();
    }

    public static Topic map(TopicEntity entity){
        return Topic.of(entity.getId(), entity.getName());
    }
}
