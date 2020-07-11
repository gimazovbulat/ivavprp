package ru.itis.ivavprp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.ivavprp.dto.EmploymentType;
import ru.itis.ivavprp.dto.VacancyDto;
import ru.itis.ivavprp.dto.WorkSchedule;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "employment_type")
    private EmploymentType employmentType;
    private String text;
    private Date time;
    @Enumerated(EnumType.STRING)
    @Column(name = "work_schedule")
    private WorkSchedule workSchedule;
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
                .text(vacancyDto.getText())
                .time(vacancyDto.getTime())
                .workSchedule(vacancyDto.getWorkSchedule())
                .build();
    }

    public static VacancyDto toVacancyDto(Vacancy vacancy) {
        return VacancyDto.builder()
                .id(vacancy.getId())
                .employmentType(vacancy.getEmploymentType())
                .name(vacancy.getName())
                .text(vacancy.getText())
                .time(vacancy.getTime())
                .workSchedule(vacancy.getWorkSchedule())
                .build();
    }
}
