package org.example.application.task.use_cases.run;

import java.io.IOException;
import java.util.List;
public interface TestRunner {
    TestRunResult run(LanguageDto language, ExecutionContext executionContext) throws IOException;
}
