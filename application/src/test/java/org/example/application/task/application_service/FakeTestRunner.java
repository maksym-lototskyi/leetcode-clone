package org.example.application.task.application_service;

import org.example.application.task.use_cases.run.TestRunResult;
import org.example.application.task.use_cases.run.TestRunner;

import java.util.List;

public class FakeTestRunner implements TestRunner {
    private TestRunResult userResult;
    private TestRunResult workingSolutionResult;

    public void setUserResult(TestRunResult result) {
        this.userResult = result;
    }

    public void setWorkingSolutionResult(TestRunResult result) {
        this.workingSolutionResult = result;
    }

    private boolean firstCall = true;

    @Override
    public TestRunResult run(List<Object> inputs, String code, String runtimeImage) {
        if (firstCall) {
            firstCall = false;
            return userResult;
        }
        return workingSolutionResult;
    }
}
