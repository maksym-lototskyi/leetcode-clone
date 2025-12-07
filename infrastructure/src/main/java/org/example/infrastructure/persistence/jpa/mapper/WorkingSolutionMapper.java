package org.example.infrastructure.persistence.jpa.mapper;

import org.example.domain.model.task.WorkingSolution;
import org.example.infrastructure.persistence.jpa.model.LanguageEntity;
import org.example.infrastructure.persistence.jpa.model.WorkingSolutionEntity;

public class WorkingSolutionMapper {
    public static WorkingSolutionEntity map(WorkingSolution workingSolution, LanguageEntity language){
        WorkingSolutionEntity entity = new WorkingSolutionEntity();
        entity.setId(workingSolution.id().value());
        entity.setSourceCode(workingSolution.sourceCode());
        entity.setLanguage(language);
        return entity;
    }
}
