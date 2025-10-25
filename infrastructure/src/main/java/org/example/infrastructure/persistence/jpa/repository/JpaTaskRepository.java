package org.example.infrastructure.persistence.jpa.repository;

import org.example.application.task.ports.out.TaskRepository;
import org.example.domain.task.Task;
import org.example.domain.task.TaskId;
import org.example.domain.task.TaskSummary;
import org.example.domain.task.service.IOValidator;
import org.example.infrastructure.persistence.jpa.mapper.TaskMapper;
import org.example.infrastructure.persistence.jpa.model.TaskEntity;
import org.example.infrastructure.persistence.jpa.model.TopicEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
class JpaTaskRepository implements TaskRepository {
    private final JpaTaskEntityRepository jpaTaskEntityRepository;
    private final JpaTopicEntityRepository jpaTopicEntityRepository;
    private final IOValidator validator;

    public JpaTaskRepository(JpaTaskEntityRepository jpaTaskEntityRepository, JpaTopicEntityRepository jpaTopicEntityRepository, IOValidator validator) {
        this.jpaTaskEntityRepository = jpaTaskEntityRepository;
        this.jpaTopicEntityRepository = jpaTopicEntityRepository;
        this.validator = validator;
    }

    @Override
    public Optional<Task> findById(TaskId taskId) {
        return jpaTaskEntityRepository.findById(taskId.value())
                .map(t -> TaskMapper.map(t, validator));
    }

    @Override
    public List<TaskSummary> findTaskSummaries(int pageNumber, int pageSize) {
        return jpaTaskEntityRepository.findAllSummaries(Pageable.ofSize(pageSize).withPage(pageNumber)).toList();
    }

    @Override
    public boolean existsById(TaskId taskId) {
        return jpaTaskEntityRepository.existsById(taskId.value());
    }

    @Override
    public Task save(Task task) {
        List<TopicEntity> topics = jpaTopicEntityRepository.findAllById(task.getTopics()
                .stream()
                .map(t -> t.topicId().value())
                .toList());
        TaskEntity saved = jpaTaskEntityRepository.save(TaskMapper.map(task, topics));
        return TaskMapper.map(saved, validator);
    }
}
