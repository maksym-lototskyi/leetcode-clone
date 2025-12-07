package org.example.application.task.use_cases.get_task_page;

import org.example.application.task.ports.out.TaskRepository;
import org.example.domain.model.task.TaskSummary;

import java.util.List;

class GetTaskPageUseCase implements GetTaskPageInputBoundary {
    private final TaskRepository taskRepository;

    public GetTaskPageUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskSummary> execute(int pageNumber, int pageSize) {
        return taskRepository.findTaskSummaries(pageNumber, pageSize);
    }
}
