package org.example.infrastructure.persistence.jpa.repository;

import org.example.application.exception.NotFoundException;
import org.example.application.task.ports.out.TaskRepository;
import org.example.domain.language.Language;
import org.example.domain.task.Task;
import org.example.domain.task.TaskId;
import org.example.domain.task.TaskSummary;
import org.example.domain.task.TestCase;
import org.example.domain.task.service.IOValidator;
import org.example.domain.topic.TopicId;
import org.example.infrastructure.persistence.jpa.mapper.TaskMapper;
import org.example.infrastructure.persistence.jpa.mapper.TestCaseMapper;
import org.example.infrastructure.persistence.jpa.mapper.WorkingSolutionMapper;
import org.example.infrastructure.persistence.jpa.model.LanguageEntity;
import org.example.infrastructure.persistence.jpa.model.TaskEntity;
import org.example.infrastructure.persistence.jpa.model.TopicEntity;
import org.example.infrastructure.persistence.jpa.model.WorkingSolutionEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
class JpaTaskRepository implements TaskRepository {
    private final JpaTaskEntityRepository jpaTaskEntityRepository;
    private final JpaTopicEntityRepository jpaTopicEntityRepository;
    private final JpaLanguageEntityRepository jpaLanguageEntityRepository;
    private final IOValidator validator;

    public JpaTaskRepository(JpaTaskEntityRepository jpaTaskEntityRepository, JpaTopicEntityRepository jpaTopicEntityRepository, JpaLanguageEntityRepository jpaLanguageEntityRepository, IOValidator validator) {
        this.jpaTaskEntityRepository = jpaTaskEntityRepository;
        this.jpaTopicEntityRepository = jpaTopicEntityRepository;
        this.jpaLanguageEntityRepository = jpaLanguageEntityRepository;
        this.validator = validator;
    }

    @Override
    public Optional<Task> loadTaskDefinition(TaskId taskId) {
        return jpaTaskEntityRepository.findById(taskId.value())
                .map(t -> TaskMapper.map(t, validator));
    }

    @Override
    public Optional<Task> loadTaskForRuntime(TaskId taskId) {
        return jpaTaskEntityRepository.findTaskForRuntimeByTaskId(taskId.value())
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
    @Transactional
    public void save(Task task) {
        List<TopicEntity> topics = jpaTopicEntityRepository.findAllById(task.getTopics().stream().map(TopicId::value).toList());

        WorkingSolutionEntity workingSolution = null;
        if(task.getWorkingSolution() != null){
            LanguageEntity language = jpaLanguageEntityRepository.findById(task.getWorkingSolution().languageId().value())
                    .orElseThrow(() -> new NotFoundException("Language not found with id: " + task.getWorkingSolution().languageId().value()));

            workingSolution = WorkingSolutionMapper.map(task.getWorkingSolution(), language);
        }
        jpaTaskEntityRepository.save(TaskMapper.map(task, topics, workingSolution));
    }
}
