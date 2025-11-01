package org.example.infrastructure.adapters;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.example.domain.task.service.IOValidator;
import org.springframework.stereotype.Component;

@Component
public class JsonIOValidator implements IOValidator {
    @Override
    public boolean isValid(String value) {
        try {
            JsonParser.parseString(value);
            return true;
        } catch (JsonSyntaxException e) {
            return false;
        }
    }
}
