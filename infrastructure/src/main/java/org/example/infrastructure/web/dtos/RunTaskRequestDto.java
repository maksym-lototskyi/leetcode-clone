package org.example.infrastructure.web.dtos;

import com.google.gson.JsonObject;

import java.util.UUID;

public record RunTaskRequestDto(UUID taskId, JsonObject input, String sourceCode, String language) {
}
