package org.example.application.language.use_cases.create;

import org.example.domain.model.language.LanguageName;

public record CreateLanguageCommand(LanguageName name, String version, String extension) {
}
