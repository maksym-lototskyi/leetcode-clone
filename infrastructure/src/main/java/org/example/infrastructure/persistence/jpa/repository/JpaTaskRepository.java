package org.example.infrastructure.persistence.jpa.repository;

import org.example.application.task.ports.out.TaskRepository;
import org.example.domain.model.task.*;
import org.example.infrastructure.persistence.jpa.mapper.TaskMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
class JpaTaskRepository implements TaskRepository {
    private final JpaTaskEntityRepository jpaTaskEntityRepository;
    private final JpaDraftTaskEntityRepository jpaDraftTaskEntityRepository;
    private final JpaPublishedTaskEntityRepository jpaPublishedTaskEntityRepository;
    private final TaskMapper mapper;

    public JpaTaskRepository(JpaTaskEntityRepository jpaTaskEntityRepository, JpaDraftTaskEntityRepository jpaDraftTaskEntityRepository, JpaPublishedTaskEntityRepository jpaPublishedTaskEntityRepository, TaskMapper mapper) {
        this.jpaTaskEntityRepository = jpaTaskEntityRepository;
        this.jpaDraftTaskEntityRepository = jpaDraftTaskEntityRepository;
        this.jpaPublishedTaskEntityRepository = jpaPublishedTaskEntityRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<PublishedTask> findPublishedTaskDefinition(TaskId taskId) {
        return jpaPublishedTaskEntityRepository.findById(taskId.value())
                .map(entity -> (PublishedTask) mapper.toDomain(entity));
    }

    @Override
    public Optional<DraftTask> findDraftById(TaskId taskId) {
        return jpaDraftTaskEntityRepository.findById(taskId.value())
                .map(entity -> (DraftTask) mapper.toDomain(entity));
    }

    @Override
    public Optional<PublishedTask> findPublishedTaskById(TaskId taskId) {
        return jpaPublishedTaskEntityRepository.findById(taskId.value())
                .map(entity -> (PublishedTask) mapper.toDomain(entity));
    }


    @Override
    public List<TaskSummary> findPublishedTaskPage(int pageNumber, int pageSize) {
        return jpaTaskEntityRepository.findAllSummaries(Pageable.ofSize(pageSize).withPage(pageNumber)).toList();
    }

    @Override
    public boolean existsById(TaskId taskId) {
        return jpaTaskEntityRepository.existsById(taskId.value());
    }

    @Override
    @Transactional
    public void save(Task task) {
        jpaTaskEntityRepository.save(mapper.toEntity(task));
    }
}
