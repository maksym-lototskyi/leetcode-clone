package org.example.application.task.use_cases.run;

import java.util.List;
public interface TestRunner {
    TestRunResult run(List<Object> parsedInputs, String code, String runtimeImage);
}
