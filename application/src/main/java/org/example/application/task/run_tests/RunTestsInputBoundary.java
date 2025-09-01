package org.example.application.task.run_tests;

public interface RunTestsInputBoundary {
    RunTestsResultDto execute(RunTestsCommand command);
}
