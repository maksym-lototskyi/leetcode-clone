package org.example.infrastructure.adapters;

import org.example.domain.task.service.IOValidator;
import org.springframework.stereotype.Component;

@Component
public class JsonIOValidator implements IOValidator {
    @Override
    public boolean isValid(String value) {
        return false;
    }
}
