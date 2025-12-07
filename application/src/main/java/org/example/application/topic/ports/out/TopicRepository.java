package org.example.application.topic.ports.out;

import org.example.domain.model.topic.Topic;
import org.example.domain.model.topic.TopicId;

import java.util.List;

public interface TopicRepository {
    List<Topic> findAllByIds(List<TopicId> topicIds);
    boolean existAllByIds(List<TopicId> topicIds);
}
