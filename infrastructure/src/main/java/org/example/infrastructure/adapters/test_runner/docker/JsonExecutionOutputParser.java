package org.example.infrastructure.adapters.test_runner.docker;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
class JsonExecutionOutputParser implements ExecutionOutputParser{
    @Override
    public ParsedOutput parse(String output) {
        Gson gson = new Gson();
        return gson.fromJson(output, ParsedOutput.class);
    }
}
