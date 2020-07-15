package ru.itis.ivavprp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.Month;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VacancyDto {
    private Long id;
    private String name;
    private EmploymentType employmentType;
    private String text;
    private LocalDateTime time;
    private WorkSchedule workSchedule;
    private String emplTypeToShow;
    private String workScheduleToShow;
    private int minSalary;
    private int maxSalary;
    private Month month;
    private int day;
}
