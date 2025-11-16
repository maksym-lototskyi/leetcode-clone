package org.example.application.task.use_cases.add_working_solution;

import org.example.application.class_definition.ports.out.ClassDefinitionRepository;
import org.example.application.exception.NotFoundException;
import org.example.application.language.ports.out.LanguageRepository;
import org.example.application.task.use_cases.run.AdditionalClassDto;
import org.example.application.task.use_cases.submit.TestCaseEvaluator;
import org.example.domain.class_definition.ClassDefinition;
import org.example.domain.language.Language;
import org.example.domain.submission.SubmissionResultStatus;
import org.example.domain.task.Task;
import org.example.domain.task.WorkingSolution;
import org.example.domain.task.exception.WorkingSolutionNotValidException;
import org.example.domain.task.service.WorkingSolutionValidator;

import java.util.List;

public class WorkingSolutionValidatorImpl implements WorkingSolutionValidator {
    private final TestCaseEvaluator testCaseEvaluator;
    private final ClassDefinitionRepository classDefinitionRepository;
    private final LanguageRepository languageRepository;

    public WorkingSolutionValidatorImpl(TestCaseEvaluator testCaseEvaluator, ClassDefinitionRepository classDefinitionRepository, LanguageRepository languageRepository) {
        this.testCaseEvaluator = testCaseEvaluator;
        this.classDefinitionRepository = classDefinitionRepository;
        this.languageRepository = languageRepository;
    }

    @Override
    public void validate(Task task, WorkingSolution workingSolution) {
        List<ClassDefinition> definitions = classDefinitionRepository.findAllByIds(task.getRelatedClassDefinitions());
        List<AdditionalClassDto> additionalClasses = definitions.stream()
                .map(cd -> new AdditionalClassDto(cd.className(), cd.getImplementationFor(workingSolution.languageId())
                        .orElseThrow(() -> new NotFoundException("Implementation for class " + cd.className()))
                        .sourceCode()
                ))
                .toList();

        Language language = languageRepository.findById(workingSolution.languageId())
                .orElseThrow(() -> new NotFoundException("Language not found: " + workingSolution.languageId()));
        var results = testCaseEvaluator.evaluateTestCases(task, language, additionalClasses, workingSolution.sourceCode());
        throw new WorkingSolutionNotValidException(results.failingTestCase().testCaseId(), results.status());
    }
}
