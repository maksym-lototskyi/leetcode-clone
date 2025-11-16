package org.example.application.task.use_cases.run;

import org.example.domain.language.Language;

public class LanguageMapper {
    public static LanguageDto map(Language language){
        return new LanguageDto(language.getName().value(), language.getFileExtension().value());
    }
}
