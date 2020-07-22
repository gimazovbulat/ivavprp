package ru.itis.ivavprp.services;

import org.springframework.stereotype.Component;
import ru.itis.ivavprp.dto.ResumeVacancyDto;
import ru.itis.ivavprp.models.ResumeVacancy;
import ru.itis.ivavprp.models.Status;
import ru.itis.ivavprp.repositories.ResumeVacancyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResumeVacancyServiceImpl implements ResumeVacancyService {
    private final ResumeVacancyRepository resumeVacancyRepository;

    public ResumeVacancyServiceImpl(ResumeVacancyRepository resumeVacancyRepository) {
        this.resumeVacancyRepository = resumeVacancyRepository;
    }

    @Override
    public List<ResumeVacancyDto> getAllByCompanyIdNotChecked(Long companyId) {
        return resumeVacancyRepository
                .findAllByVacancy_Company_IdAndStatus(companyId, Status.NOT_CHECKED)
                .stream()
                .map(ResumeVacancy::toResumeVacancyDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResumeVacancyDto> getAllByStudentId(Long studentId) {
        return resumeVacancyRepository
                .findAllByResume_Student_Id(studentId)
                .stream()
                .map(ResumeVacancy::toResumeVacancyDto)
                .collect(Collectors.toList());
    }

    @Override
    public void changeStatus(Long id, Status status) {
        ResumeVacancy resumeVacancy = resumeVacancyRepository.getOne(id);
        resumeVacancy.setStatus(status);
        resumeVacancyRepository.save(resumeVacancy);
    }

    @Override
    public void save(ResumeVacancyDto resumeVacancyDto) {
        resumeVacancyRepository.save(ResumeVacancy.fromResumeVacancyDto(resumeVacancyDto));
    }
}
