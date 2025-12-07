package org.example.application.task.use_cases.create;

import org.example.application.class_definition.ports.out.ClassDefinitionRepository;
import org.example.application.exception.NotFoundException;
import org.example.application.task.ports.out.TaskRepository;
import org.example.application.topic.ports.out.TopicRepository;
import org.example.domain.model.class_definition.ClassDefinitionId;
import org.example.domain.model.task.Task;
import org.example.domain.model.task.TaskDescription;
import org.example.domain.model.task.TaskLevel;
import org.example.domain.model.task.TaskSignature;
import org.example.domain.model.topic.TopicId;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

class CreateTaskUseCase implements CreateTaskInputBoundary{
    private final TaskRepository taskRepository;
    private final TopicRepository topicRepository;
    private final ClassDefinitionRepository classDefinitionRepository;

    public CreateTaskUseCase(TaskRepository taskRepository, TopicRepository topicRepository, ClassDefinitionRepository classDefinitionRepository) {
        this.taskRepository = taskRepository;
        this.topicRepository = topicRepository;
        this.classDefinitionRepository = classDefinitionRepository;
    }

    public UUID execute(CreateTaskCommand command) {
        boolean topicsExist = topicRepository.existAllByIds(
                command.topicIds().stream().map(TopicId::new).toList()
        );

        if (!topicsExist) {
            throw new NotFoundException("One or more topics not found");
        }

        List<String> foundNames = classDefinitionRepository.findExistingNames(command.classDefinitionNames());
        List<String> notFoundClasses = getNotFoundClasses(foundNames, command.classDefinitionNames());

        if(!notFoundClasses.isEmpty()) throw new NotFoundException("Classes not found for names: " + notFoundClasses);

        List<TaskSignature.Parameter> parameters = createParameters(command.parameterTypes(), command.parameterNames());
        List<ClassDefinitionId> definitionIds = classDefinitionRepository.findIdsByNames(command.classDefinitionNames());

        Task task = Task.draft(
                TaskSignature.of(
                        command.functionName(),
                        parameters,
                        command.returnType()
                ),
                command.title(),
                TaskDescription.of(command.description()),
                TaskLevel.valueOf(command.taskLevel()),
                command.topicIds().stream().map(TopicId::new).toList(),
                command.constraints().stream().map(Task.Constraint::new).toList(),
                definitionIds,
                command.timeLimitMs(),
                command.memoryLimitKb()
        );

        taskRepository.save(task);
        return task.getTaskId().value();
    }

    private List<String> getNotFoundClasses(List<String> foundClasses, Set<String> classDefinitionNames){
        return classDefinitionNames.stream()
                .filter(name -> !foundClasses.contains(name))
                .toList();
    }

    private List<TaskSignature.Parameter> createParameters(List<String> types, List<String> names) {
        if (types.size() != names.size()) {
            throw new IllegalArgumentException("Parameter types and names must have the same length");
        }
        List<TaskSignature.Parameter> params = new ArrayList<>();
        for (int i = 0; i < types.size(); i++) {
            params.add(new TaskSignature.Parameter(names.get(i), types.get(i)));
        }
        return params;
    }
}
