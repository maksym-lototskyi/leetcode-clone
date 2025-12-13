package org.example.application.topic.ports.out;

import org.example.domain.model.topic.Topic;
import org.example.domain.model.topic.TopicId;

import java.util.Collection;
import java.util.List;

public interface TopicRepository {
    List<Topic> findAllByIds(Collection<TopicId> topicIds);
    boolean existAllByIds(Collection<TopicId> topicIds);
}
