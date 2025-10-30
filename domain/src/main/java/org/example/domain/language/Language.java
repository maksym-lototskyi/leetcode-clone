package org.example.domain.language;

import lombok.Getter;

@Getter
public final class Language {
    private final LanguageId id;
    private final String name;
    private Version version;
    private final String fileExtension;

    public Language(LanguageId languageId, String name, Version version, String fileExtension) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be null or blank");
        if (version == null) throw new IllegalArgumentException("Version cannot be null");
        if(languageId == null) throw new IllegalArgumentException("Language id cannot be null");
        if(fileExtension == null || fileExtension.isBlank()) throw new IllegalArgumentException("File extension cannot be null or blank");

        this.id = languageId;
        this.name = name;
        this.version = version;
        this.fileExtension = fileExtension;
    }

    public static Language create(String name, Version version, String fileExtension) {
        return new Language(LanguageId.generate(), name, version, fileExtension);
    }

    public void updateVersion(Version version) {
        if (version == null) throw new IllegalArgumentException("Version cannot be null");
        this.version = version;
    }
}

