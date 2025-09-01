package org.example.application.task.run_tests;

import org.example.domain.task.Input;

public interface CustomTestCaseRunner {
    TestRunResultDto runTestCase(Input input, String submittedCode, String workingCode, String runtimeImage);
}
