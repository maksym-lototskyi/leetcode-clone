package org.example.domain.language;

import lombok.Getter;

@Getter
public final class Language {
    private final LanguageId id;
    private final String name;
    private Version version;
    private RuntimeImage runtimeImage;

    public Language(LanguageId languageId, String name, Version version, RuntimeImage runtimeImage) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be null or blank");
        if (version == null) throw new IllegalArgumentException("Version cannot be null");
        if (runtimeImage == null) throw new IllegalArgumentException("Runtime image cannot be null or blank");
        if(languageId == null) throw new IllegalArgumentException("Language id cannot be null");

        this.id = languageId;
        this.name = name;
        this.version = version;
        this.runtimeImage = runtimeImage;
    }

    public static Language create(String name, Version version, RuntimeImage runtimeImage) {
        return new Language(LanguageId.generate(), name, version, runtimeImage);
    }

    public void updateVersion(Version version) {
        if (version == null) throw new IllegalArgumentException("Version cannot be null");
        this.version = version;
    }
    public void updateRuntimeImage(RuntimeImage runtimeImage) {
        if (runtimeImage == null) throw new IllegalArgumentException("Runtime image cannot be null");
        this.runtimeImage = runtimeImage;
    }
}

