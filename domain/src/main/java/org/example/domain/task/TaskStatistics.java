package org.example.domain.task;

public record TaskStatistics(long totalSubmissions, long acceptedSubmissions) {
    public TaskStatistics {
        if (totalSubmissions < 0) {
            throw new IllegalArgumentException("Total submissions cannot be negative");
        }
        if (acceptedSubmissions < 0) {
            throw new IllegalArgumentException("Accepted submissions cannot be negative");
        }
        if (acceptedSubmissions > totalSubmissions) {
            throw new IllegalArgumentException("Accepted submissions cannot be greater than total submissions");
        }
    }

    public double acceptanceRate() {
        if (totalSubmissions == 0) {
            return 0.0;
        }
        return (double) acceptedSubmissions / totalSubmissions * 100;
    }
}
