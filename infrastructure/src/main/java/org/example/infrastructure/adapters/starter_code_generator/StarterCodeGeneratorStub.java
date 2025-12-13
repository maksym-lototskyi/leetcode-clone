package org.example.infrastructure.adapters.starter_code_generator;

import org.example.application.task.use_cases.get_task_definition.StarterCodeGenerator;
import org.example.domain.model.language.LanguageName;
import org.example.domain.model.task.TaskSignature;
import org.springframework.stereotype.Component;

@Component
class StarterCodeGeneratorStub implements StarterCodeGenerator {

    @Override
    public String generateStarterCode(TaskSignature signature, LanguageName languageName) {
        return "";
    }
}
