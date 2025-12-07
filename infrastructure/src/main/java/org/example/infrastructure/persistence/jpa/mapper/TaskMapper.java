package org.example.infrastructure.persistence.jpa.mapper;

import org.example.application.class_definition.ports.out.ClassDefinitionRepository;
import org.example.application.topic.ports.out.TopicRepository;
import org.example.domain.model.class_definition.ClassDefinitionId;
import org.example.domain.model.language.LanguageId;
import org.example.domain.model.task.*;
import org.example.domain.model.task.IOValidator;
import org.example.domain.model.topic.TopicId;
import org.example.domain.model.user.UserId;
import org.example.infrastructure.persistence.jpa.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TaskMapper{
    private final IOValidator validator;
    private final ClassDefinitionMapper classDefinitionMapper;
    private final ClassDefinitionRepository classDefinitionRepository;
    private final WorkingSolutionMapper workingSolutionMapper;
    private final TopicRepository topicRepository;

    public TaskMapper(IOValidator validator, ClassDefinitionMapper classDefinitionMapper, ClassDefinitionRepository classDefinitionRepository, WorkingSolutionMapper workingSolutionMapper, TopicRepository topicRepository) {
        this.validator = validator;
        this.classDefinitionMapper = classDefinitionMapper;
        this.classDefinitionRepository = classDefinitionRepository;
        this.workingSolutionMapper = workingSolutionMapper;
        this.topicRepository = topicRepository;
    }

    public Task toDomain(TaskEntity entity) {
        return switch (entity) {
            case DraftTaskEntity draftEntity -> toDomainDraft(draftEntity);
            case PublishedTaskEntity publishedEntity ->  toDomainPublished(publishedEntity);
        };
    }

    private DraftTask toDomainDraft(DraftTaskEntity draftEntity) {
        return new DraftTask(
                new TaskId(draftEntity.getTaskId()),
                new UserId(draftEntity.getCreatedBy()),
                TaskSignatureMapper.map(draftEntity.getTaskSignature()),
                new TaskTitle(draftEntity.getTitle()),
                new TaskDescription(draftEntity.getTaskDescription()),
                draftEntity.getTaskLevel(),
                draftEntity.getTopics().stream().map(topicEntity -> TopicId.of(topicEntity.getId())).toList(),
                draftEntity.getConstraints().stream().map(Task.Constraint::new).toList(),
                draftEntity.getExamples().stream().map(example -> ExampleMapper.map(example, validator)).toList(),
                draftEntity.getTestCases().stream().map(t -> TestCaseMapper.map(t, validator)).toList(),
                draftEntity.getRelatedClassDefinitions().stream()
                        .map(cd -> ClassDefinitionId.of(cd.getId()))
                        .toList(),
                draftEntity.getWorkingSolution() != null ? WorkingSolutionMapper.map(draftEntity.getWorkingSolution()) : null,
                draftEntity.getTimeLimitMs(),
                draftEntity.getMemoryLimitKb()
        );
    }

    private PublishedTask toDomainPublished(PublishedTaskEntity publishedEntity) {
        return new PublishedTask(
                new TaskId(publishedEntity.getTaskId()),
                new UserId(publishedEntity.getCreatedBy()),
                TaskSignatureMapper.map(publishedEntity.getTaskSignature()),
                new TaskTitle(publishedEntity.getTitle()),
                new TaskDescription(publishedEntity.getTaskDescription()),
                publishedEntity.getTaskLevel(),
                publishedEntity.getTopics().stream().map(topicEntity -> TopicId.of(topicEntity.getId())).toList(),
                publishedEntity.getConstraints().stream().map(Task.Constraint::new).toList(),
                publishedEntity.getExamples().stream().map(example -> ExampleMapper.map(example, validator)).toList(),
                publishedEntity.getTestCases().stream().map(t -> TestCaseMapper.map(t, validator)).toList(),
                publishedEntity.getRelatedClassDefinitions().stream()
                        .map(cd -> ClassDefinitionId.of(cd.getId()))
                        .toList(),
                publishedEntity.getWorkingSolution() != null ? WorkingSolutionMapper.map(publishedEntity.getWorkingSolution()) : null,
                publishedEntity.getTimeLimitMs(),
                publishedEntity.getMemoryLimitKb(),
                publishedEntity.isArchived()
        );
    }

    public TaskEntity toEntity(Task domain) {
        return switch (domain) {
            case DraftTask draftTask -> toEntityDraft(draftTask);
            case PublishedTask publishedTask -> toEntityPublished(publishedTask);
        };
    }

    private DraftTaskEntity toEntityDraft(DraftTask draftTask) {

        DraftTaskEntity draftEntity = new DraftTaskEntity();
        copyCommon(draftTask, draftEntity);

        if (draftTask.getWorkingSolution() != null) {
            draftEntity.setWorkingSolution(workingSolutionMapper.map(draftTask.getWorkingSolution(), draftEntity));
        }

        return draftEntity;
    }

    private PublishedTaskEntity toEntityPublished(PublishedTask published) {

        PublishedTaskEntity publishedEntity = new PublishedTaskEntity();
        copyCommon(published, publishedEntity);

        publishedEntity.setWorkingSolution(workingSolutionMapper.map(published.getWorkingSolution(), publishedEntity));
        publishedEntity.setArchived(published.isArchived());

        return publishedEntity;
    }

    private void copyCommon(Task domainTask, TaskEntity taskEntity) {
        taskEntity.setExamples(domainTask.getExamples().stream()
                .map(ex -> ExampleMapper.map(ex, taskEntity))
                .collect(Collectors.toSet()));

        taskEntity.setTestCases(domainTask.getTestCases().stream()
                .map(tc -> TestCaseMapper.map(tc, taskEntity))
                .collect(Collectors.toSet()));

        taskEntity.setRelatedClassDefinitions(
                domainTask.getRelatedClassDefinitions().stream()
                        .map(classDefinitionId -> classDefinitionRepository.findById(classDefinitionId)
                                .map(classDefinitionMapper::map)
                                .orElseThrow(() -> new IllegalStateException("ClassDefinition with id " + classDefinitionId + " not found")))
                        .collect(Collectors.toSet())
        );
        taskEntity.setTaskId(domainTask.getTaskId().value());
        taskEntity.setCreatedBy(domainTask.getCreatedBy().value());
        taskEntity.setTaskSignature(TaskSignatureMapper.map(domainTask.getTaskSignature()));
        taskEntity.setTitle(domainTask.getTitle().value());
        taskEntity.setTaskDescription(domainTask.getTaskDescription().value());
        taskEntity.setTaskLevel(domainTask.getTaskLevel());
        taskEntity.setTimeLimitMs(domainTask.getTimeLimitMs());
        taskEntity.setMemoryLimitKb(domainTask.getMemoryLimitKb());

        Set<TopicEntity> topicEntities = topicRepository.findAllByIds(domainTask.getTopics().stream().toList())
                .stream().map(TopicMapper::map).collect(Collectors.toSet());

        taskEntity.setTopics(topicEntities);
        taskEntity.setConstraints(domainTask.getConstraints().stream().map(Task.Constraint::description).collect(Collectors.toSet()));
    }
}

