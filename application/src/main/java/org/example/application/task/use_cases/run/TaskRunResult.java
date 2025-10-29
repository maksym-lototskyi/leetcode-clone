package org.example.application.task.use_cases.run;

import org.example.domain.submission.SubmissionResultStatus;

import java.util.List;

public record TaskRunResult(
        String input,
        String expectedOutput,
        String actualOutput,
        boolean isPassed,
        long executionTimeMs,
        SubmissionResultStatus status
) {
}
