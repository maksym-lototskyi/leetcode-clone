package org.example.domain.task;

import lombok.Getter;

@Getter
public enum TaskLevel {
    EASY("Easy"), MEDIUM("Medium"), HARD("Hard");
    private final String value;

    TaskLevel(String value) {
        this.value = value;
    }
}
