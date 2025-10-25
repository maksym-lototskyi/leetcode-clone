package org.example.application.task.use_cases.create;

import org.example.application.exception.NotFoundException;
import org.example.application.task.ports.out.TaskRepository;
import org.example.application.topic.ports.out.TopicRepository;
import org.example.domain.task.Task;
import org.example.domain.task.TaskDescription;
import org.example.domain.task.TaskLevel;
import org.example.domain.task.TaskSignature;
import org.example.domain.topic.Topic;
import org.example.domain.topic.TopicId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class CreateTaskUseCase implements CreateTaskInputBoundary{
    private final TaskRepository taskRepository;
    private final TopicRepository topicRepository;
    private final ConstraintValidator constraintValidator;

    public CreateTaskUseCase(TaskRepository taskRepository, TopicRepository topicRepository, ConstraintValidator constraintValidator) {
        this.taskRepository = taskRepository;
        this.topicRepository = topicRepository;
        this.constraintValidator = constraintValidator;
    }

    public UUID execute(CreateTaskCommand command) {
        for (String constraint : command.constraints()) {
            if (!constraintValidator.isValid(constraint)) {
                throw new IllegalArgumentException("Invalid constraint: " + constraint);
            }
        }
        List<Topic> topics = topicRepository.findAll(command.topicIds().stream().map(TopicId::new).toList());
        if (topics.size() != command.topicIds().size()) {
            throw new NotFoundException("One or more topics not found");
        }

        List<TaskSignature.Parameter> parameters = createParameters(command.parameterTypes(), command.parameterNames());

        Task task = Task.draft(
                TaskSignature.of(
                        command.functionName(),
                        parameters,
                        command.returnType()
                ),
                command.title(),
                TaskDescription.of(command.description()),
                TaskLevel.valueOf(command.taskLevel()),
                topics,
                command.constraints().stream().map(Task.Constraint::new).toList(),
                command.timeLimitMs(),
                command.memoryLimitKb()
        );

        Task saved = taskRepository.save(task);
        return saved.getTaskId().value();
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
