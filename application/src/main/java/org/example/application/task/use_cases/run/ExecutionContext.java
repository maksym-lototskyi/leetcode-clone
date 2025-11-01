package org.example.application.task.use_cases.run;

import java.util.List;
import java.util.Map;

public record ExecutionContext(
        String userCode,
        String input,
        String signature,
        List<AdditionalClassDto> additionalClasses
) {
}
