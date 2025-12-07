package org.example.domain.model.task;

import org.example.domain.validation.ValidationUtils;

import java.util.List;
import java.util.Objects;

public record TaskSignature(
        String methodName,
        List<Parameter> parameters,
        String returnType
) {
    public record Parameter(String name, String type) {
        public Parameter {
            ValidationUtils.requireNonBlank(name, "Parameter name cannot be null or blank");
            ValidationUtils.requireNonBlank(type, "Parameter type cannot be null or blank");
        }
    }

    public TaskSignature {
        ValidationUtils.requireNonBlank(methodName, "Function name cannot be null or blank");
        ValidationUtils.requireNonEmptyCollection(parameters, "Parameters cannot be null or empty");
        ValidationUtils.requireNonBlank(returnType, "Return type cannot be null or blank");
    }

    public static TaskSignature of(String functionName, List<Parameter> parameters, String returnType) {
        return new TaskSignature(functionName, parameters, returnType);
    }
}

