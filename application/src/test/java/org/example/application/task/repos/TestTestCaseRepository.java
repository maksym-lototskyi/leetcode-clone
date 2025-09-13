package org.example.application.task.repos;

import org.example.application.task.ports.out.TestCaseRepository;
import org.example.domain.task.TaskId;
import org.example.domain.task.TestCase;

import java.util.List;

public class TestTestCaseRepository implements TestCaseRepository {
    private TestCase testCase;
    @Override
    public List<TestCase> findAllByTaskId(TaskId taskId) {
        return List.of(testCase);
    }

    @Override
    public TestCase save(TestCase testCase) {
        return null;
    }

    @Override
    public boolean existsByTaskId(TaskId taskId) {
        return false;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }
}
