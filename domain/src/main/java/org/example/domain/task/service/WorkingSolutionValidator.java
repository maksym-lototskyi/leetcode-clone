package org.example.domain.task.service;

import org.example.domain.language.Language;
import org.example.domain.task.Task;
import org.example.domain.task.WorkingSolution;

public interface WorkingSolutionValidator {
    void validate(Task task, WorkingSolution workingSolution);
}

