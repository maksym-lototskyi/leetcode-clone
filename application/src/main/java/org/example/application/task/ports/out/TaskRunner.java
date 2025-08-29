package org.example.application.task.ports.out;

import org.example.application.task.submit.RunTestCaseCommand;
import org.example.application.task.submit.TestRunResult;

public interface TaskRunner {
    TestRunResult run(RunTestCaseCommand command);
}
