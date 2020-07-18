package ru.itis.ivavprp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
    @Fetch(FetchMode.SELECT)
    @JoinTable(schema = "ivavprp", name = "skills_vacancies",
            joinColumns = @JoinColumn(name = "vacancy_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skills;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

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
                .company(vacancy.getCompany())
                .minSalary(vacancy.getMinSalary())
                .workScheduleToShow(vacancy.getWorkScheduleToShow())
                .emplTypeToShow(vacancy.getEmplTypeToShow())
                .companyId(vacancy.getCompany().getId())
                .companyName(vacancy.getCompany().getName())
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

    @Override
    public String toString() {
        return "Vacancy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employmentType=" + employmentType +
                ", emplTypeToShow='" + emplTypeToShow + '\'' +
                ", minSalary=" + minSalary +
                ", maxSalary=" + maxSalary +
                ", text='" + text + '\'' +
                ", time=" + time +
                ", workSchedule=" + workSchedule +
                ", workScheduleToShow='" + workScheduleToShow + '\'' +
                ", skills=" + skills +
                '}';
    }
}
