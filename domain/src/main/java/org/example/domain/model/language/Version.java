package org.example.domain.model.language;

import org.example.domain.validation.ValidationUtils;

public record Version (String value){
    public Version{
        ValidationUtils.requireNonBlank(value, "Version cannot be null or blank");
    }

    public static Version of(String version) {
        return new Version(version);
    }
}
