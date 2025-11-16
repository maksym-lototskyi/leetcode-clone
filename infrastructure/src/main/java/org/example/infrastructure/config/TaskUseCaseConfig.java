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
import org.example.application.task.use_cases.create.CreateTaskInputBoundary;
import org.example.application.task.use_cases.create.CreateTaskInputBoundaryFactory;
import org.example.application.task.use_cases.publish.PublishTaskInputBoundary;
import org.example.application.task.use_cases.publish.PublishTaskInputBoundaryFactory;
import org.example.application.task.use_cases.run.ObjectConverter;
import org.example.application.task.use_cases.run.RunTaskInputBoundary;
import org.example.application.task.use_cases.run.RunTaskInputBoundaryFactory;
import org.example.application.task.use_cases.run.TestRunner;
import org.example.application.task.use_cases.submit.TestCaseEvaluator;
import org.example.application.topic.ports.out.TopicRepository;
import org.example.domain.task.TestCase;
import org.example.domain.task.service.IOValidator;
import org.example.domain.task.service.WorkingSolutionValidator;
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
    public CreateTaskInputBoundary createTaskInputBoundary(TaskRepository taskRepository, TopicRepository topicRepository, ClassDefinitionRepository classDefinitionRepository) {
        return CreateTaskInputBoundaryFactory.create(
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
    public PublishTaskInputBoundary publishTaskInputBoundary(TaskRepository taskRepository) {
        return PublishTaskInputBoundaryFactory.create(taskRepository);
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
