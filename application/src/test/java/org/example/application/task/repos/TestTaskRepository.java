package org.example.application.task.repos;

import org.example.application.task.ports.out.TaskRepository;
import org.example.domain.task.*;
import org.example.domain.topic.Topic;
import org.example.domain.topic.TopicId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TestTaskRepository implements TaskRepository {
    private Task task = Task.draft(new TaskSignature("fun", List.of(new TaskSignature.Parameter("n", "int")), "int"),
            TaskDescription.empty(),
            TaskLevel.HARD,
            List.of(new Topic(new TopicId(UUID.randomUUID()), "Arrays")),
            List.of(new Task.Constraint("1 <= n <= 2^32")),
            2,
            2);
    public void setWorkingSolution(WorkingSolution workingSolution){
        task.addWorkingSolution(workingSolution);
    }
    @Override
    public Optional<Task> findById(TaskId taskId) {
        return Optional.of(task);
    }

    @Override
    public List<TaskSummary> findTaskSummaries(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public Task save(Task task) {
        return null;
    }

    @Override
    public boolean existsById(TaskId taskId) {
        return false;
    }
}
