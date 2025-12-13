package org.example.infrastructure.config;

import org.example.application.class_definition.ports.out.ClassDefinitionRepository;
import org.example.application.language.ports.out.LanguageRepository;
import org.example.application.task.ports.out.TaskRepository;
import org.example.application.task.use_cases.add_example.AddExampleInputBoundary;
import org.example.application.task.use_cases.add_example.AddExampleInputBoundaryFactory;
import org.example.application.task.use_cases.add_test_case.AddTestCaseInputBoundary;
import org.example.application.task.use_cases.add_test_case.AddTestCaseInputBoundaryFactory;
import org.example.application.task.use_cases.add_working_solution.AddWorkingSolutionInputBoundary;
import org.example.application.task.use_cases.add_working_solution.AddWorkingSolutionInputBoundaryFactory;
import org.example.application.task.use_cases.add_working_solution.WorkingSolutionValidatorImpl;
import org.example.application.task.use_cases.create_draft.CreateDraftTaskInputBoundary;
import org.example.application.task.use_cases.create_draft.CreateDraftTaskInputBoundaryFactory;
import org.example.application.task.use_cases.get_task_definition.GetTaskDefinitionInputBoundary;
import org.example.application.task.use_cases.get_task_definition.GetTaskDefinitionInputBoundaryFactory;
import org.example.application.task.use_cases.get_task_definition.StarterCodeGenerator;
import org.example.application.task.use_cases.publish.PublishTaskInputBoundary;
import org.example.application.task.use_cases.publish.PublishTaskInputBoundaryFactory;
import org.example.application.task.use_cases.run.ObjectConverter;
import org.example.application.task.use_cases.run.RunTaskInputBoundary;
import org.example.application.task.use_cases.run.RunTaskInputBoundaryFactory;
import org.example.application.task.use_cases.run.TestRunner;
import org.example.application.task.use_cases.submit.TestCaseEvaluator;
import org.example.application.topic.ports.out.TopicRepository;
import org.example.domain.model.task.IOValidator;
import org.example.domain.model.task.WorkingSolutionValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskUseCaseConfig {
    @Bean
    public RunTaskInputBoundary runTaskInputBoundary(LanguageRepository languageRepository, TaskRepository taskRepository, ClassDefinitionRepository classDefinitionRepository, TestRunner testRunner, ObjectConverter converter) {
        return RunTaskInputBoundaryFactory.create(
                languageRepository,
                taskRepository,
                classDefinitionRepository,
                testRunner,
                converter
        );
    }

    @Bean
    public CreateDraftTaskInputBoundary createTaskInputBoundary(TaskRepository taskRepository, TopicRepository topicRepository, ClassDefinitionRepository classDefinitionRepository) {
        return CreateDraftTaskInputBoundaryFactory.create(
                taskRepository,
                topicRepository,
                classDefinitionRepository
        );
    }

    @Bean
    public AddExampleInputBoundary addExampleInputBoundary(TaskRepository taskRepository, IOValidator ioValidator) {
        return AddExampleInputBoundaryFactory.create(
                taskRepository,
                ioValidator
        );
    }

    @Bean
    public AddTestCaseInputBoundary addTestCaseInputBoundary(TaskRepository taskRepository, IOValidator ioValidator) {
        return AddTestCaseInputBoundaryFactory.create(taskRepository, ioValidator);
    }

    @Bean
    public AddWorkingSolutionInputBoundary addWorkingSolutionInputBoundary(TaskRepository taskRepository, LanguageRepository languageRepository, WorkingSolutionValidator validator) {
        return AddWorkingSolutionInputBoundaryFactory.create(taskRepository, languageRepository, validator);
    }

    @Bean
    public PublishTaskInputBoundary publishTaskInputBoundary(TaskRepository taskRepository, WorkingSolutionValidator validator) {
        return PublishTaskInputBoundaryFactory.create(taskRepository,validator);
    }

    @Bean
    public GetTaskDefinitionInputBoundary getTaskDefinitionInputBoundary(TaskRepository taskRepository, TopicRepository topicRepository, LanguageRepository languageRepository, StarterCodeGenerator starterCodeGenerator) {
        return GetTaskDefinitionInputBoundaryFactory.create(
                taskRepository,
                topicRepository,
                languageRepository,
                starterCodeGenerator
        );
    }

    @Bean
    public TestCaseEvaluator testCaseEvaluator (TestRunner testRunner, ObjectConverter converter){
        return new TestCaseEvaluator(testRunner, converter);
    }

    @Bean
    public WorkingSolutionValidator validator(TestCaseEvaluator evaluator,  ClassDefinitionRepository classDefinitionRepository, LanguageRepository repository){
        return new WorkingSolutionValidatorImpl(evaluator, classDefinitionRepository, repository);
    }
}
