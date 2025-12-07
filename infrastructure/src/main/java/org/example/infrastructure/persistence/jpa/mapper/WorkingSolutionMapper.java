package org.example.infrastructure.persistence.jpa.mapper;

import org.example.application.exception.NotFoundException;
import org.example.application.language.ports.out.LanguageRepository;
import org.example.domain.model.language.Language;
import org.example.domain.model.language.LanguageId;
import org.example.domain.model.task.WorkingSolution;
import org.example.domain.model.task.WorkingSolutionId;
import org.example.infrastructure.persistence.jpa.model.LanguageEntity;
import org.example.infrastructure.persistence.jpa.model.TaskEntity;
import org.example.infrastructure.persistence.jpa.model.WorkingSolutionEntity;
import org.springframework.stereotype.Component;

@Component
public class WorkingSolutionMapper {
    private final LanguageRepository languageRepository;

    public WorkingSolutionMapper(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public WorkingSolutionEntity map(WorkingSolution workingSolution, TaskEntity task){
        Language language = languageRepository.findById(workingSolution.languageId())
                .orElseThrow(() -> new NotFoundException("Language not found with id: " + workingSolution.languageId().value()));

        WorkingSolutionEntity entity = new WorkingSolutionEntity();
        entity.setId(workingSolution.id().value());
        entity.setSourceCode(workingSolution.sourceCode());
        entity.setLanguage(LanguageMapper.map(language));
        entity.setTask(task);
        return entity;
    }

    public static WorkingSolution map(WorkingSolutionEntity entity){
        return new WorkingSolution(
                WorkingSolutionId.of(entity.getId()),
                LanguageId.of(entity.getLanguage().getId()),
                entity.getSourceCode()
        );
    }
}
