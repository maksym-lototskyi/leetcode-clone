package org.example.application.task.ports.out;

import org.example.domain.task.TaskId;
import org.example.domain.task.TestCase;

import java.util.List;

public interface TestCaseRepository {
    List<TestCase> findAllByTaskId(TaskId taskId);
}
