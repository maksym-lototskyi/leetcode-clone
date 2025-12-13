package org.example.application.task.use_cases.get_task_definition;

import org.example.domain.model.task.Task;
import org.example.domain.model.topic.Topic;

import java.util.List;

class TaskDefinitionMapper {
    public static TaskDetailsDto toDto(Task task, List<Topic> topics, String starterCode) {
        return new TaskDetailsDto(
                task.getTaskId().value(),
                task.getTitle().value(),
                task.getTaskDescription().value(),
                starterCode,
                task.getTaskLevel(),
                topics.stream()
                        .map(Topic::name)
                        .toList(),
                task.getExamples().stream()
                        .map(example -> new ExampleDto(
                                example.input().value(),
                                example.output().value(),
                                example.explanation()
                        ))
                        .toList(),
                task.getConstraints().stream()
                        .map(Task.Constraint::description)
                        .toList(),
                List.of()
        );
    }
}
