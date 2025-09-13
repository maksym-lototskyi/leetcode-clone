package org.example.domain.task.service;

import org.example.domain.task.Task;
import org.example.domain.task.TestCase;
import org.example.domain.task.WorkingSolution;

import java.util.List;

public interface WorkingSolutionValidator {
    void validate(Task task, WorkingSolution workingSolution, List<TestCase> testCases);
}

