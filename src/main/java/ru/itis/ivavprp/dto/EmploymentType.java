package ru.itis.ivavprp.dto;

import lombok.Getter;

@Getter
public enum EmploymentType {
    FULL_TIME("Full time"), PART_TIME("Part time"), TEMPORARY("Temporary employment"),
    VOLUNTEERING("Volunteering"), WORK_PLACEMENT("Work_placement");

    private String value;

    EmploymentType(String value) {
        this.value = value;
    }
}
