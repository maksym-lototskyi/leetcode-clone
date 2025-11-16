package org.example.domain.language;

import lombok.Getter;

@Getter
public final class Language {
    private final LanguageId id;
    private final LanguageName name;
    private Version version;
    private final FileExtension fileExtension;

    public Language(LanguageId languageId, LanguageName name, Version version, FileExtension fileExtension) {
        if (name == null) throw new IllegalArgumentException("Name cannot be null");
        if (version == null) throw new IllegalArgumentException("Version cannot be null");
        if(languageId == null) throw new IllegalArgumentException("Language id cannot be null");
        if(fileExtension == null) throw new IllegalArgumentException("File extension cannot be null");

        this.id = languageId;
        this.name = name;
        this.version = version;
        this.fileExtension = fileExtension;
    }

    public static Language create(LanguageName name, Version version, FileExtension fileExtension) {
        return new Language(LanguageId.generate(), name, version, fileExtension);
    }

    public void updateVersion(Version version) {
        if (version == null) throw new IllegalArgumentException("Version cannot be null");
        this.version = version;
    }
}

