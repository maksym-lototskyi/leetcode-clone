package org.example.infrastructure.persistence.jpa.repository;

import org.example.application.topic.ports.out.TopicRepository;
import org.example.domain.model.topic.Topic;
import org.example.domain.model.topic.TopicId;
import org.example.infrastructure.persistence.jpa.mapper.TopicMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class JpaTopicRepository implements TopicRepository {
    private final JpaTopicEntityRepository jpaTopicEntityRepository;

    public JpaTopicRepository(JpaTopicEntityRepository jpaTopicEntityRepository) {
        this.jpaTopicEntityRepository = jpaTopicEntityRepository;
    }

    @Override
    public List<Topic> findAllByIds(Collection<TopicId> topicIds) {
        return jpaTopicEntityRepository.findAllById(
                topicIds.stream().map(TopicId::value).toList()
        ).stream().map(TopicMapper::map).toList();
    }

    @Override
    public boolean existAllByIds(Collection<TopicId> topicIds) {
        return jpaTopicEntityRepository.existAllByIds(
                topicIds.stream().map(TopicId::value).toList()
        );
    }
}
