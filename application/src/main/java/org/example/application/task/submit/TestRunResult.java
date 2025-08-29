package org.example.application.task.submit;

public record TestRunResult(
        String input,
        String expectedOutput,
        String actualOutput,
        long executionTimeMs,
        long memoryUsageKb,
        boolean isPassed
) {}
