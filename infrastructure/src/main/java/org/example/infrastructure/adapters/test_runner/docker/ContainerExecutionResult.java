package org.example.infrastructure.adapters.test_runner.docker;

record ContainerExecutionResult(
        int exitCode,
        String stdout,
        String stderr) {
}
