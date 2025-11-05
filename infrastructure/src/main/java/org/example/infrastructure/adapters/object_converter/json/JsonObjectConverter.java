package org.example.infrastructure.adapters.object_converter.json;

import com.google.gson.Gson;
import org.example.application.task.use_cases.run.ObjectConverter;
import org.springframework.stereotype.Component;

@Component
class JsonObjectConverter implements ObjectConverter {
    @Override
    public <S> String convert(S source) {
        Gson gson = new Gson();
        return gson.toJson(source);
    }
}
