package org.example.application.task.use_cases.run;

public interface RunTaskInputBoundary {
    TaskRunResult execute(RunTaskCommand command);
}
