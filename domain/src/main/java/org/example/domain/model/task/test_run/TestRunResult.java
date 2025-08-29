package org.example.domain.model.task.test_run;

import org.example.domain.model.submission.SubmissionResultStatus;

import java.util.List;

public record TestRunResult(SubmissionResultStatus status, long executionTimeMs, List<TestRun> testRuns) {
}
