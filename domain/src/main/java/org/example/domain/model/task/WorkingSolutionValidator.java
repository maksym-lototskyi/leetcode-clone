package org.example.domain.model.task;

import org.example.domain.model.task.Task;
import org.example.domain.model.task.WorkingSolution;

public interface WorkingSolutionValidator {
    void validate(DraftTask task, WorkingSolution workingSolution);
}

