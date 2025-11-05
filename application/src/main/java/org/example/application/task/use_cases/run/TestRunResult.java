package org.example.application.task.use_cases.run;

public record TestRunResult(
        String result,
        String error,
        String stdout,
        ErrorType errorType,
        long executionTimeMs,
        long memoryUsageKb
) {
    public static TestRunResult success(String result, String stdout, long executionTimeMs, long memoryUsageKb) {
        return new TestRunResult(result, null, stdout,null, executionTimeMs, memoryUsageKb);
    }
    public static TestRunResult runtimeError(String error, String stdout, long executionTimeMs, long memoryUsageKb) {
        return new TestRunResult(null, error, stdout, ErrorType.RUNTIME_ERROR, executionTimeMs, memoryUsageKb);
    }
    public static TestRunResult compilationError(String error) {
        return new TestRunResult(null, error, null, ErrorType.COMPILATION_ERROR, 0, 0);
    }
}
