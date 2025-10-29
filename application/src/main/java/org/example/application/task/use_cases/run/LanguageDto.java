package org.example.application.task.use_cases.run;

import java.util.List;

public record LanguageDto(
        String name,
        String fileExtension,
        String runtimeImage
) {
}
