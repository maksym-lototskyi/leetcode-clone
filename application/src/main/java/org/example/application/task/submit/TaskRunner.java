package org.example.application.task.submit;

import org.example.application.task.run_tests.TestRunResultDto;

public interface TaskRunner {
    TestRunResultDto run(RunTestCaseCommand command);
}
