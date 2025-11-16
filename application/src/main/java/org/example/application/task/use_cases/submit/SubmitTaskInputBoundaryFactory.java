package org.example.application.task.use_cases.submit;

import org.example.application.class_definition.ports.out.ClassDefinitionRepository;
import org.example.application.language.ports.out.LanguageRepository;
import org.example.application.submission.ports.out.SubmissionRepository;
import org.example.application.task.ports.out.TaskRepository;
import org.example.application.task.use_cases.run.ObjectConverter;
import org.example.application.task.use_cases.run.TestRunner;
import org.example.application.user.ports.out.UserRepository;

public class SubmitTaskInputBoundaryFactory {
    public static SubmitTaskInputBoundary create(
            TaskRepository taskRepository,
            UserRepository userRepository,
            SubmissionRepository submissionRepository,
            LanguageRepository languageRepository,
            ClassDefinitionRepository classDefinitionRepository,
            TestCaseEvaluator testCaseEvaluator
    ){
        return new SubmitTaskUseCase(taskRepository, userRepository, submissionRepository, languageRepository, classDefinitionRepository, testCaseEvaluator);
    }
}
