package org.example.application.task.use_cases.add_test_case;

import java.util.List;
import java.util.UUID;

public record AddTestCaseCommand(
        UUID taskId,
        List<String> input,
        String expectedOutput
) {
}
