package org.example.domain.model.language;

import org.example.domain.validation.ValidationUtils;

public enum LanguageName{
    JAVA("Java"),
    PYTHON("Python"),
    JAVASCRIPT("JavaScript"),
    CSHARP("C#");

    private final String name;

    LanguageName(String name) {
        this.name = name;
    }

    public static LanguageName fromString(String name){
        ValidationUtils.requireNonBlank(name, "Language name cannot be null or empty");
        try {
            return LanguageName.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unsupported language name: " + name);
        }
    }

    public String getName() {
        return name;
    }
}
