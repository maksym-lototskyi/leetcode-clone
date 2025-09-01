package org.example.application.task.run_tests;

import org.example.domain.submission.SubmissionResultStatus;

import java.util.List;

public record RunTestsResultDto(SubmissionResultStatus status,
                                List<TestRunResultDto> results,
                                long totalRuntimeMillis) {
}
