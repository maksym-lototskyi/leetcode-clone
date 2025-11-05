package org.example.infrastructure.adapters.test_runner.docker;

interface ExecutionOutputParser {
    ParsedOutput parse(String output);
}
