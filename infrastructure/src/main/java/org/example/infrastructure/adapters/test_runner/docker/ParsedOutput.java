package org.example.infrastructure.adapters.test_runner.docker;

public record ParsedOutput(
        boolean success,
        long executionTimeMs,
        String result,
        String stdout
) {
}
