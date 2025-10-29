package org.example.application.task.use_cases.run;

public enum ErrorType {
    COMPILATION_ERROR,
    RUNTIME_ERROR;

    public static ErrorType fromErrorOutput(String errorOutput) {
        if(errorOutput.matches("(?s).*\\b(compilation error|syntax error|cannot find symbol|unresolved reference)\\b.*")) {
            return COMPILATION_ERROR;
        } else {
            return RUNTIME_ERROR;
        }
    }
}
