package org.example.domain.model.language;

import lombok.Getter;

@Getter
public final class Language {
    private final LanguageId id;
    private final String name;
    private String version;
    private String runtimeImage;

    public Language(String name, String version, String runtimeImage) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be null or blank");
        if (version == null || version.isBlank()) throw new IllegalArgumentException("Version cannot be null or blank");
        if (runtimeImage == null || runtimeImage.isBlank()) throw new IllegalArgumentException("Runtime image cannot be null or blank");

        this.id = LanguageId.generate();
        this.name = name;
        this.version = version;
        this.runtimeImage = runtimeImage;
    }

    public void updateVersion(String version) {
        if (version == null || version.isBlank()) throw new IllegalArgumentException("Version cannot be null or blank");
        this.version = version;
    }
    public void updateRuntimeImage(String runtimeImage) {
        if (runtimeImage == null || runtimeImage.isBlank()) throw new IllegalArgumentException("Runtime image cannot be null or blank");
        this.runtimeImage = runtimeImage;
    }
}

