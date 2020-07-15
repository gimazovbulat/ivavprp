package ru.itis.ivavprp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.ivavprp.dto.EmploymentType;
import ru.itis.ivavprp.dto.VacancyDto;
import ru.itis.ivavprp.dto.WorkSchedule;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(schema = "ivavprp", name = "vacancy")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "employment_type")
    private EmploymentType employmentType;
    @Transient
    private String emplTypeToShow;
    @Column(name = "min")
    private Integer minSalary;
    @Column(name = "max")
    private Integer maxSalary;
    private String text;
    private LocalDateTime time;
    @Enumerated(EnumType.STRING)
    @Column(name = "work_schedule")
    private WorkSchedule workSchedule;
    @Transient
    private String workScheduleToShow;
    @ManyToMany
    @JoinTable(schema = "ivavprp", name = "skills_vacancies",
            joinColumns = @JoinColumn(name = "vacancy_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skills;

    public static Vacancy fromVacancyDto(VacancyDto vacancyDto) {
        return Vacancy.builder()
                .id(vacancyDto.getId())
                .employmentType(vacancyDto.getEmploymentType())
                .name(vacancyDto.getName())
                .maxSalary(vacancyDto.getMaxSalary())
                .minSalary(vacancyDto.getMinSalary())
                .text(vacancyDto.getText())
                .time(vacancyDto.getTime())
                .workScheduleToShow(vacancyDto.getWorkScheduleToShow())
                .emplTypeToShow(vacancyDto.getEmplTypeToShow())
                .workSchedule(vacancyDto.getWorkSchedule())
                .build();
    }

    public static VacancyDto toVacancyDto(Vacancy vacancy) {
        return VacancyDto.builder()
                .id(vacancy.getId())
                .employmentType(vacancy.getEmploymentType())
                .name(vacancy.getName())
                .text(vacancy.getText())
                .maxSalary(vacancy.getMaxSalary())
                .minSalary(vacancy.getMinSalary())
                .workScheduleToShow(vacancy.getWorkScheduleToShow())
                .emplTypeToShow(vacancy.getEmplTypeToShow())
                .time(vacancy.getTime())
                .month(vacancy.getTime().getMonth())
                .day(vacancy.getTime().getDayOfMonth())
                .workSchedule(vacancy.getWorkSchedule())
                .build();
    }

    //fills ..ToShow variables with names that will be shown in view
    @PostLoad
    private void fillValuesToShow() {
        emplTypeToShow = employmentType.getValueToShow();
        workScheduleToShow = workSchedule.getValueToShow();
    }
}
