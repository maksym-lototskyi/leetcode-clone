package org.example.infrastructure.adapters.test_runner;

record ContainerExecutionResult(
        int exitCode,
        String stdout,
        String stderr) {
}
