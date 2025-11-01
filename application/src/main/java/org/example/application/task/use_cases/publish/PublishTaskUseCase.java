package org.example.application.task.use_cases.publish;

import org.example.application.exception.NotFoundException;
import org.example.application.task.ports.out.TaskRepository;
import org.example.domain.task.TaskId;
import org.example.domain.task.TestCase;

import java.util.List;
import java.util.UUID;

public class PublishTaskUseCase implements PublishTaskInputBoundary {
    private final TaskRepository taskRepository;

    public PublishTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void execute(UUID taskId){
        var task = taskRepository.findById(TaskId.of(taskId))
                .orElseThrow(() -> new NotFoundException("Task not found with id: " + taskId));

        List<TestCase> testCases = taskRepository.findAllTestCasesByTaskId(task.getTaskId());
        if(testCases.isEmpty()) throw new IllegalStateException("Cannot publish task without test cases");

        task.publish();
        taskRepository.save(task);
    }
}
