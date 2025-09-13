package org.example.application.task.domain_service;

import org.example.domain.task.service.IOValidator;

public class TestIOValidator implements IOValidator {
    @Override
    public boolean isValid(String value) {
        return true;
    }
}
