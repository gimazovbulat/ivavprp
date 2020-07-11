package ru.itis.ivavprp.dto;

import lombok.Getter;

@Getter
public enum WorkSchedule {
    FULL_TIME("Full time"), FLEXIBLE("Flexible schedule"), REMOTE("Remote working");

    private String value;

    WorkSchedule(String value) {
        this.value = value;
    }
}
