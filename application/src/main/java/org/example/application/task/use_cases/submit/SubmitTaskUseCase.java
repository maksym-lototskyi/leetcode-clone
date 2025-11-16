package org.example.application.task.use_cases.submit;

import org.example.application.class_definition.ports.out.ClassDefinitionRepository;
import org.example.application.exception.NotFoundException;
import org.example.application.language.ports.out.LanguageRepository;
import org.example.application.submission.ports.out.SubmissionRepository;
import org.example.application.task.ports.out.TaskRepository;
import org.example.application.task.use_cases.add_working_solution.ExecutionMetadata;
import org.example.application.task.use_cases.run.*;
import org.example.application.user.ports.out.UserRepository;
import org.example.domain.class_definition.ClassDefinition;
import org.example.domain.language.Language;
import org.example.domain.submission.Submission;
import org.example.domain.submission.SubmissionResult;
import org.example.domain.submission.TestRun;
import org.example.domain.task.Task;
import org.example.domain.task.TaskId;
import org.example.domain.task.TestCase;
import org.example.domain.user.UserId;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

class SubmitTaskUseCase implements SubmitTaskInputBoundary{
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final SubmissionRepository submissionRepository;
    private final LanguageRepository languageRepository;
    private final ClassDefinitionRepository classDefinitionRepository;
    private final TestCaseEvaluator testCaseEvaluator;

    public SubmitTaskUseCase(TaskRepository taskRepository, UserRepository userRepository, SubmissionRepository submissionRepository, LanguageRepository languageRepository, ClassDefinitionRepository classDefinitionRepository, TestCaseEvaluator testCaseEvaluator) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.submissionRepository = submissionRepository;
        this.languageRepository = languageRepository;
        this.classDefinitionRepository = classDefinitionRepository;
        this.testCaseEvaluator = testCaseEvaluator;
    }

    public void execute(SubmitTaskCommand command, UUID userId) throws IOException {
        Task task = taskRepository.loadTaskForRuntime(TaskId.of(command.taskId()))
                .orElseThrow(() -> new NotFoundException("Task not found with id: " + command.taskId()));
        if(!userRepository.existsById(UserId.of(userId))){
            throw new NotFoundException("User not found with id: " + userId);
        }
        Language language = languageRepository.findByName(command.language())
                .orElseThrow(() -> new NotFoundException("Language not found with name: " + command.language()));

        Submission submission = new Submission(task.getTaskId(), UserId.of(userId), language.getId(), command.sourceCode());
        submissionRepository.save(submission);

        List<ClassDefinition> relatedClassDefinitions = classDefinitionRepository.findAllByIds(task.getRelatedClassDefinitions());
        List<AdditionalClassDto> additionalClasses = ClassImplementationMapper.convertToAdditionalClassDtos(relatedClassDefinitions, language.getId());

        SubmissionResult result = testCaseEvaluator.evaluateTestCases(task, language, additionalClasses, command.sourceCode());

        submission.attachResult(result);
        submissionRepository.save(submission);
    }

}
