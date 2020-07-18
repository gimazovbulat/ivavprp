package ru.itis.ivavprp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.ivavprp.models.Company;

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
    private Company company;
    private Long companyId;
    private String companyName;
    private int day;


    @Override
    public String toString() {
        return "VacancyDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employmentType=" + employmentType +
                ", text='" + text + '\'' +
                ", time=" + time +
                ", companyId=" + companyId +
                ", companyName=" + companyName +
                ", workSchedule=" + workSchedule +
                ", emplTypeToShow='" + emplTypeToShow + '\'' +
                ", workScheduleToShow='" + workScheduleToShow + '\'' +
                ", minSalary=" + minSalary +
                ", maxSalary=" + maxSalary +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}
