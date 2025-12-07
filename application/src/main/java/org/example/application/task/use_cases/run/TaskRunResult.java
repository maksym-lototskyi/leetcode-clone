package org.example.application.task.use_cases.run;

import org.example.domain.model.submission.SubmissionResultStatus;

public record TaskRunResult(
        String input,
        String expectedOutput,
        String actualOutput,
        String errorOutput,
        String stdout,
        boolean isPassed,
        long executionTimeMs,
        SubmissionResultStatus status
) {
}
