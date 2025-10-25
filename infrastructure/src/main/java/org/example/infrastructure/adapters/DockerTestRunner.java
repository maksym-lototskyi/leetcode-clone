package org.example.infrastructure.adapters;

import org.example.application.task.use_cases.run.TestRunResult;
import org.example.application.task.use_cases.run.TestRunner;

import java.util.List;

public class DockerTestRunner implements TestRunner {
    @Override
    public TestRunResult run(List<Object> parsedInputs, String code, String runtimeImage) {
        return null;
    }
}
