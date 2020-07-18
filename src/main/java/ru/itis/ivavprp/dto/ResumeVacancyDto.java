package ru.itis.ivavprp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.ivavprp.models.Resume;
import ru.itis.ivavprp.models.Status;
import ru.itis.ivavprp.models.Vacancy;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResumeVacancyDto {
    private Long id;
    private Vacancy vacancy;
    private Resume resume;
    private Status status;
}
