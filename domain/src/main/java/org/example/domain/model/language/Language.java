package org.example.domain.model.language;

import lombok.Getter;
import org.example.domain.validation.ValidationUtils;

@Getter
public final class Language {
    public static final LanguageName DEFAULT = LanguageName.JAVA;

    private final LanguageId id;
    private final LanguageName name;
    private Version version;
    private final FileExtension fileExtension;

    public Language(LanguageId languageId, LanguageName name, Version version, FileExtension fileExtension) {
        this.id = ValidationUtils.requireNonNull(languageId, "Language id cannot be null");
        this.name = ValidationUtils.requireNonNull(name, "Language name cannot be null");
        this.version = ValidationUtils.requireNonNull(version, "Version cannot be null");
        this.fileExtension = ValidationUtils.requireNonNull(fileExtension, "File extension cannot be null");
    }

    public static Language create(LanguageName name, Version version, FileExtension fileExtension) {
        return new Language(LanguageId.generate(), name, version, fileExtension);
    }

    public void updateVersion(Version version) {
        this.version = ValidationUtils.requireNonNull(version, "Version cannot be null");
    }
}

