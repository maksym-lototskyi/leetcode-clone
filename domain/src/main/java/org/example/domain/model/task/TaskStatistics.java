package org.example.domain.model.task;

import org.example.domain.validation.ValidationUtils;

public record TaskStatistics(long totalSubmissions, long acceptedSubmissions) {
    public TaskStatistics {
        ValidationUtils.requireNonNegative(totalSubmissions, "Total submissions cannot be negative");
        ValidationUtils.requireNonNegative(acceptedSubmissions, "Accepted submissions cannot be negative");
        ValidationUtils.requireCondition(acceptedSubmissions <= totalSubmissions, "Accepted submissions cannot exceed total submissions");
    }

    public double acceptanceRate() {
        if (totalSubmissions == 0) {
            return 0.0;
        }
        return (double) acceptedSubmissions / totalSubmissions * 100;
    }
}
