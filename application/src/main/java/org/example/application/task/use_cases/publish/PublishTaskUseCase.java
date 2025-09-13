package org.example.application.task.use_cases.publish;

import org.example.application.exception.NotFoundException;
import org.example.application.task.ports.out.TaskRepository;
import org.example.application.task.ports.out.TestCaseRepository;
import org.example.domain.task.TaskId;
import org.example.domain.task.TestCase;

import java.util.List;
import java.util.UUID;

public class PublishTaskUseCase implements PublishTaskInputBoundary {
    private final TaskRepository taskRepository;
    private final TestCaseRepository testCaseRepository;

    public PublishTaskUseCase(TaskRepository taskRepository, TestCaseRepository testCaseRepository) {
        this.taskRepository = taskRepository;
        this.testCaseRepository = testCaseRepository;
    }

    public void execute(UUID taskId){
        var task = taskRepository.findById(TaskId.of(taskId))
                .orElseThrow(() -> new NotFoundException("Task not found with id: " + taskId));

        List<TestCase> testCases = testCaseRepository.findAllByTaskId(TaskId.of(taskId));

        task.publish(testCases);
        taskRepository.save(task);
    }
}
