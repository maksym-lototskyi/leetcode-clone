package org.example.application.task.use_cases.run;

import java.io.IOException;

public interface RunTaskInputBoundary {
    TaskRunResult execute(RunTaskCommand command) throws IOException;
}
