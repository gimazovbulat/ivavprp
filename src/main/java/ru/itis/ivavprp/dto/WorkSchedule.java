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

    public static WorkSchedule getWorkSchedule(String name) {
        WorkSchedule[] values = WorkSchedule.values();
        for (WorkSchedule empl : values) {
            if (empl.getValue().equals(name)) {
                return empl;
            }
        }
        throw new IllegalStateException();
    }

}
