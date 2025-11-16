package org.example.application.language.use_cases.create;

import java.util.UUID;

public interface CreateLanguageInputBoundary {
    UUID execute(CreateLanguageCommand request);
}
