package org.example.application.task.use_cases.get_task_definition;

import org.example.domain.model.language.LanguageName;
import org.example.domain.model.task.TaskSignature;

public interface StarterCodeGenerator {
    String generateStarterCode(TaskSignature signature, LanguageName languageName);
}

