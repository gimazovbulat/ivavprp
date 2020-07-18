package ru.itis.ivavprp.dto;

import lombok.Getter;

@Getter
public enum EmploymentType {
    FULL_TIME("FULL_TIME", "Full time"),
    PART_TIME("PART_TIME", "Part time"),
    TEMPORARY("TEMPORARY", "Temporary"),
    VOLUNTEERING("VOLUNTEERING", "Volunteering"),
    WORK_PLACEMENT("WORK_PLACEMENT", "Work placement");

    private final String value;
    private final String valueToShow;

    EmploymentType(String value, String valueToShow) {
        this.value = value;
        this.valueToShow = valueToShow;
    }

    public static EmploymentType getEmploymentType(String name) {
        EmploymentType[] values = EmploymentType.values();
        for (EmploymentType empl : values) {
            if (empl.getValue().equals(name)) {
                return empl;
            }
        }
        throw new IllegalStateException();
    }
}
