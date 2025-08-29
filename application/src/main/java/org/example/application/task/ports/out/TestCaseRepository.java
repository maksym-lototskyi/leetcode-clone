package org.example.application.task.ports.out;

import org.example.domain.model.task.TaskId;
import org.example.domain.model.task.test_case.TestCase;

import java.util.List;

public interface TestCaseRepository {
    List<TestCase> findAllByTaskId(TaskId taskId);
}
