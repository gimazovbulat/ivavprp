package ru.itis.ivavprp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.ivavprp.dto.ResumeDto;
import ru.itis.ivavprp.dto.ResumeVacancyDto;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(schema = "ivavprp", name = "resume_vacancy")
public class ResumeVacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;
    @Enumerated(EnumType.STRING)
    private Status status;

    public static ResumeVacancy fromResumeVacancyDto(ResumeVacancyDto resumeVacancyDto) {
        return ResumeVacancy.builder()
                .id(resumeVacancyDto.getId())
                .resume(resumeVacancyDto.getResume())
                .vacancy(resumeVacancyDto.getVacancy())
                .status(resumeVacancyDto.getStatus())
                .build();
    }

    public static ResumeVacancyDto toResumeVacancyDto(ResumeVacancy resumeVacancy) {
        return ResumeVacancyDto.builder()
                .id(resumeVacancy.id)
                .resume(resumeVacancy.resume)
                .status(resumeVacancy.status)
                .vacancy(resumeVacancy.vacancy)
                .build();
    }
}
