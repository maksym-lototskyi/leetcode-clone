package org.example.infrastructure.web.controllers;

import org.example.application.language.use_cases.create.CreateLanguageCommand;
import org.example.application.language.use_cases.create.CreateLanguageInputBoundary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/languages")
public class LanguageController {
    private final CreateLanguageInputBoundary createLanguageUseCase;

    public LanguageController(CreateLanguageInputBoundary createLanguageUseCase) {
        this.createLanguageUseCase = createLanguageUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<UUID> createLanguage(@RequestBody CreateLanguageCommand command) {
        var languageId = createLanguageUseCase.execute(command);
        return ResponseEntity.ok(languageId);
    }
}
