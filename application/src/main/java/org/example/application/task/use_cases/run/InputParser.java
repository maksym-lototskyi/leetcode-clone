package org.example.application.task.use_cases.run;

import org.example.domain.task.TaskSignature;

import java.util.List;

public interface InputParser {
    List<Object> parse(TaskSignature signature, List<String> rawInput);
}
