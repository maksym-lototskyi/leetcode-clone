package org.example.application.task.run_tests;

import org.example.domain.task.Input;
import org.example.domain.task.TaskId;

import java.util.List;

public record RunTestsCommand(TaskId taskId, List<Input> inputs, String sourceCode, String language) {
}
