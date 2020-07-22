package ru.itis.ivavprp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class VacancyForm {
    private Long id;
    private Long companyId;
    private String name;
    private String text;
    private String employmentType;
    private String workSchedule;
    private Integer minSalary;
    private Integer maxSalary;
    private List<Long> skillsIds;
}
