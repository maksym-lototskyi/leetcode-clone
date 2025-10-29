package org.example.application.task.use_cases.run;

import java.util.Map;

public record ExecutionContext(
        String userCode,
        String input,
        String signature,
        Map<String, String> customClassDefinitions
) {
}
