package org.example.application.task.use_cases.run;

public record TestRunResult(
        String output,
        String error,
        ErrorType errorType,
        long executionTimeMs,
        long memoryUsageKb
) {
    public static TestRunResult runtimeError(String error, long executionTimeMs, long memoryUsageKb) {
        return new TestRunResult(null, error, ErrorType.RUNTIME_ERROR, executionTimeMs, memoryUsageKb);
    }
    public static TestRunResult compilationError(String error) {
        return new TestRunResult(null, error, ErrorType.COMPILATION_ERROR, 0, 0);
    }
}
