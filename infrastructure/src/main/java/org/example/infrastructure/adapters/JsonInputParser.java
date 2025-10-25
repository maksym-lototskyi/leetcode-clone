package org.example.infrastructure.adapters;

import org.example.application.task.use_cases.run.InputParser;
import org.example.domain.task.TaskSignature;

import java.util.List;

public class JsonInputParser implements InputParser {
    @Override
    public List<Object> parse(TaskSignature signature, List<String> rawInput) {
        return null;
    }
}
