package ru.itis.ivavprp.dto;

import lombok.Getter;

@Getter
public enum WorkSchedule {
    FULL_DAY("FULL_DAY", "Full day"),
    FLEXIBLE_SCHEDULE("FLEXIBLE_SCHEDULE", "Flexible schedule"),
    REMOTE_WORKING("REMOTE_WORKING", "Remote working");

    private final String value;
    private final String valueToShow;

    WorkSchedule(String value, String valueToShow) {
        this.value = value;
        this.valueToShow = valueToShow;
    }

    @Override
    public String toString() {
        return "WorkSchedule{" +
                "value='" + value + '\'' +
                ", valueToShow='" + valueToShow + '\'' +
                '}';
    }
}
