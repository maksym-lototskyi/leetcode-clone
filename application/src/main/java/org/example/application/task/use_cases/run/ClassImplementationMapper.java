package org.example.application.task.use_cases.run;

import org.example.application.exception.NotFoundException;
import org.example.domain.model.class_definition.ClassDefinition;
import org.example.domain.model.language.LanguageId;

import java.util.ArrayList;
import java.util.List;

public class ClassImplementationMapper {
    public static List<AdditionalClassDto> convertToAdditionalClassDtos(List<ClassDefinition> definitions, LanguageId languageId){
        List<AdditionalClassDto> dtos = new ArrayList<>();
        for(var def : definitions){
            dtos.add(new AdditionalClassDto(def.className(), def.getImplementationFor(languageId).orElseThrow(() ->
                    new NotFoundException("Class definition not found for class definition "+ def.className() + " and language id: " + languageId)
            ).sourceCode()));
        }
        return dtos;
    }
}
