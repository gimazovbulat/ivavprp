package ru.itis.ivavprp.services;

import org.springframework.stereotype.Component;
import ru.itis.ivavprp.dto.ResumeVacancyDto;
import ru.itis.ivavprp.models.Status;
import ru.itis.ivavprp.models.Vacancy;

import java.util.List;

public interface ResumeVacancyService  {

    List<ResumeVacancyDto> getAllByCompanyIdNotChecked(Long companyId);

    List<ResumeVacancyDto> getAllByStudentId(Long studentId);

    void changeStatus(Long id, Status status);

    void save(ResumeVacancyDto resumeVacancyDto);

}
