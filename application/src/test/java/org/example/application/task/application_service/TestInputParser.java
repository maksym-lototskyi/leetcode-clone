package org.example.application.task.application_service;

import org.example.application.task.use_cases.run.InputParser;
import org.example.domain.task.TaskSignature;

import java.util.List;

public class TestInputParser implements InputParser {
    @Override
    public List<Object> parse(TaskSignature signature, List<String> rawInput) {
        return List.of(2);
    }
}
