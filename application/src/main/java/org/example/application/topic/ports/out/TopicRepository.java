package org.example.application.topic.ports.out;

import org.example.domain.topic.Topic;
import org.example.domain.topic.TopicId;

import java.util.List;

public interface TopicRepository {
    List<Topic> findAll(List<TopicId> topicIds);
}
