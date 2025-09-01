package org.example.application.task.run_tests;

public record TestRunResultDto(
        String input,
        String expectedOutput,
        String actualOutput,
        long executionTimeMs,
        long memoryUsageKb,
        boolean isPassed
) {}
