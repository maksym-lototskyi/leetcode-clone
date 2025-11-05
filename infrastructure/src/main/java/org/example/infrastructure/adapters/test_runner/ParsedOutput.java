package org.example.infrastructure.adapters.test_runner;

public record ParsedOutput(
        boolean success,
        long executionTimeMs,
        String result,
        String stdout
) {
}
